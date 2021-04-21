package com.system.service.impl;

import com.system.domain.OrderInfo;
import com.system.domain.PassengerInfo;
import com.system.domain.RechargeRecord;
import com.system.domain.TicketsInfo;
import com.system.domain.vo.AirlineOrderaccount;
import com.system.domain.vo.UserRechargeVo;
import com.system.mapper.OrderInfoMapper;
import com.system.mapper.PassengerInfoMapper;
import com.system.mapper.RechargeRecordMapper;
import com.system.mapper.TicketsInfoMapper;
import com.system.service.OrderServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class OrderServicesImpl implements OrderServices {
    @Autowired
    private OrderInfoMapper orderInfoMapper;

    @Autowired
    private PassengerInfoMapper passengerInfoMapper;

    @Autowired
    private TicketsInfoMapper ticketsInfoMapper;

    @Autowired
    private RechargeRecordMapper rechargeRecordMapper;


    @Override
    public String createmyOrder(Integer ticketId, Integer passengerId, Integer purchase_number) {
        TicketsInfo ticketsInfo = ticketsInfoMapper.selectByPrimaryKey(ticketId);
        PassengerInfo passengerInfo = passengerInfoMapper.selectByPrimaryKey(passengerId);
        float pWallent = passengerInfo.getPassengerAccount();
        //判断是否有余票
        int ticket_number = ticketsInfo.getTicketNumber();
        if (purchase_number > ticket_number) {
            return "机票不足";
        }
        else if (ticket_number == 0) {
            return "机票已售空";
        }
        else {
            //机票充足、计算订单金额
            float ticket_price = ticketsInfo.getTicketPrice();
            float order_price = ticket_price*purchase_number;
            //查询用户金额是否足以支付账单
            if(pWallent >= order_price) {
                //插入数据状态
                OrderInfo record = new OrderInfo();

                //机票信息
                Date purchaseDate = new Date();

                String startplace = ticketsInfo.getTicketStartplace();
                String endplace = ticketsInfo.getTicketEndplace();
                Date startime = ticketsInfo.getTicketBegintime();
                Date endtime = ticketsInfo.getTicketEndtime();

                //获取乘客信息
                String passenger_name = passengerInfo.getPassengerName();
                String passenger_identity = passengerInfo.getPassengerIdentity();
                String passenger_phone = passengerInfo.getPassengerPhone();

                //订单状态
                String order_status = "支付成功";

                //将数据插入订单表中
                record.setPurchaseDate(purchaseDate);
                record.setTicketId(ticketId);
                record.setTicketPrice(ticket_price);
                record.setStartplace(startplace);
                record.setEndplace(endplace);
                record.setStarttime(startime);
                record.setEndtime(endtime);
                record.setPassengerId(passengerId);
                record.setPassengerName(passenger_name);
                record.setPassengerIdentity(passenger_identity);
                record.setPassengerPhone(passenger_phone);
                record.setOrderStatus(order_status);
                record.setPurchaseNumber(purchase_number);
                record.setOrderPrice(order_price);
                orderInfoMapper.insertSelective(record);

                //同时需要更新用户金额信息、钱包信息、机票信息

                //1.更新用户信息
                float uppassenger_account = (pWallent-order_price);
                passengerInfo.setPassengerAccount(uppassenger_account);
                passengerInfoMapper.updateByPrimaryKeySelective(passengerInfo);

                //插入钱包支付记录信息
                RechargeRecord rechargeRecord =new RechargeRecord();
                rechargeRecord.setPassengerId(passengerId);
                rechargeRecord.setPassengerName(passenger_name);
                rechargeRecord.setPassengerIdentity(passenger_identity);
                rechargeRecord.setPassengerPhone(passenger_phone);
                rechargeRecord.setRechargeAccount(-order_price);
                rechargeRecord.setOptionName("购买机票");
                rechargeRecord.setRechargeTime(purchaseDate);
                rechargeRecordMapper.insertSelective(rechargeRecord);

                //更新机票信息
                int upticket_number = (ticket_number - purchase_number);
                ticketsInfo.setTicketNumber(upticket_number);
                ticketsInfoMapper.updateByPrimaryKeySelective(ticketsInfo);

                return "余额充足，支付成功";
            }
            else {
                return "余额不足，请充值";
            }
        }
    }

    @Override
    public String refundmyOrder(OrderInfo orderInfo) {
        //获取用户id
        Integer passgerId = orderInfo.getPassengerId();
        //获取机票id
        Integer ticketId = orderInfo.getTicketId();

        //由传递过来的信息，查询是否已经退过票
        String orderStatus = orderInfo.getOrderStatus();
        if (orderStatus.equals("已退票")) {
            return "已退票，请勿重复操作";
        }
        else {
            //进行退票操作

            //先更新订单信息,将订单状态改为已退票
            String uporderStatus = "已退票";
            orderInfo.setOrderStatus(uporderStatus);
            orderInfoMapper.updateByPrimaryKeySelective(orderInfo);

            //1.将金额返还给到用户账户中
            //获取该订单的总金额
            float orderprice = orderInfo.getOrderPrice();
            //由订单中的用户id查询该乘客
            PassengerInfo passengerInfo = passengerInfoMapper.selectByPrimaryKey(passgerId);
            //用户更新后的金额
            float uppassengeraccount = passengerInfo.getPassengerAccount()+orderprice;
            passengerInfo.setPassengerAccount(uppassengeraccount);
            //更新用户信息
            passengerInfoMapper.updateByPrimaryKeySelective(passengerInfo);

            //2.添加一条钱包记录信息
            RechargeRecord addrechargeRecord = new RechargeRecord();
            String passenger_name = orderInfo.getPassengerName();
            String passenger_identity = orderInfo.getPassengerIdentity();
            String passenger_phone = orderInfo.getPassengerPhone();
            String option_name = "退票金额";
            Date recharge_time = new Date();
            addrechargeRecord.setPassengerId(passgerId);
            addrechargeRecord.setPassengerName(passenger_name);
            addrechargeRecord.setPassengerIdentity(passenger_identity);
            addrechargeRecord.setPassengerPhone(passenger_phone);
            addrechargeRecord.setRechargeAccount(orderprice);
            addrechargeRecord.setOptionName(option_name);
            addrechargeRecord.setRechargeTime(recharge_time);
            rechargeRecordMapper.insertSelective(addrechargeRecord);

            //3.增加相应的机票数量
            //获取订单中的机票数量
            int oticket_number = orderInfo.getPurchaseNumber();
            //根据订单中的机票id获取机票信息
            TicketsInfo ticketsInfo = ticketsInfoMapper.selectByPrimaryKey(ticketId);
            //计算更新后的机票数量
            int upticket_number = ticketsInfo.getTicketNumber() + oticket_number;
            ticketsInfo.setTicketNumber(upticket_number);
            //更新机票信息
            ticketsInfoMapper.updateByPrimaryKeySelective(ticketsInfo);

            return "退票成功";

        }
    }

    @Override
    public List<OrderInfo> getList(Integer passengerId) {
        return orderInfoMapper.selectCurrentPOrders(passengerId);
    }

    @Override
    public String changeOrderquest(Integer orderId, Integer passengerId, Integer purchase_number, Integer ticketId) {
        //订单信息
        OrderInfo orderInfo = orderInfoMapper.selectByPrimaryKey(orderId);

        //订单信息
        //订单中的机票数量
        int orderticket_number = orderInfo.getPurchaseNumber();
        //订单中的机票信息
        //先获取订单中的机票id
        int orderticket_id = orderInfo.getTicketId();
        //获取订单中的机票信息
        TicketsInfo orderticketsInfo = ticketsInfoMapper.selectByPrimaryKey(orderticket_id);

        //用户信息
        PassengerInfo passengerInfo = passengerInfoMapper.selectByPrimaryKey(passengerId);
        int passenger_id = passengerInfo.getPassengerId();
        String passenger_name = passengerInfo.getPassengerName();
        String passenger_identity = passengerInfo.getPassengerIdentity();
        String passenger_phone = passengerInfo.getPassengerPhone();

        //改签购买机票信息
        TicketsInfo ticketsInfo = ticketsInfoMapper.selectByPrimaryKey(ticketId);
        int ticket_id = ticketsInfo.getTicketId();
        float ticket_price = ticketsInfo.getTicketPrice();
        String start_place = ticketsInfo.getTicketStartplace();
        String end_place = ticketsInfo.getTicketEndplace();
        Date start_time = ticketsInfo.getTicketBegintime();
        Date end_time = ticketsInfo.getTicketEndtime();
        int ticket_number = ticketsInfo.getTicketNumber();


        //订单信息
        //该笔订单的金额
        float orderprice = orderInfo.getOrderPrice();

        //改签机票单价
        float postticketprice = ticketsInfo.getTicketPrice();
        //改签机票总金额
        float postoderprice = postticketprice*purchase_number;
        //多退少补
        //1.相等
        if (orderprice == postoderprice) {
            String uporderstatus = "已改签";
            orderInfo.setOrderStatus(uporderstatus);
            orderInfoMapper.updateByPrimaryKeySelective(orderInfo);

            //用户信息不用更改
            //加入一条钱包记录
            RechargeRecord rechargeRecord = new RechargeRecord();
            float recharge_account = 0;
            String option_name = "签改";
            Date recharge_time = new Date();

            rechargeRecord.setPassengerId(passenger_id);
            rechargeRecord.setPassengerName(passenger_name);
            rechargeRecord.setPassengerIdentity(passenger_identity);
            rechargeRecord.setPassengerPhone(passenger_phone);
            rechargeRecord.setRechargeAccount(recharge_account);
            rechargeRecord.setOptionName(option_name);
            rechargeRecord.setRechargeTime(recharge_time);
            //插入一条钱包信息
            rechargeRecordMapper.insertSelective(rechargeRecord);


            //更新机票信息
            //1.改签后增加原有机票的数量
            int upticket_number = orderticketsInfo.getTicketNumber()+orderticket_number;
            orderticketsInfo.setTicketNumber(upticket_number);
            ticketsInfoMapper.updateByPrimaryKeySelective(orderticketsInfo);

            //减少改签时所购买机票的数量
            int upbuyticket_number = ticket_number-purchase_number;
            ticketsInfo.setTicketNumber(upbuyticket_number);
            ticketsInfoMapper.updateByPrimaryKeySelective(ticketsInfo);

            return "改签成功，价格相同，无需再次付款";
        }
        else if (orderprice > postoderprice) {
            String uporderstatus = "已改签";
            orderInfo.setOrderStatus(uporderstatus);
            orderInfoMapper.updateByPrimaryKeySelective(orderInfo);

            //进行退款操作
            //退还新订单多余的金额
            //1.计算需要退款的金额
            float rebackaccount = orderprice - postoderprice;
            //增加相应的用户账户余额
            float upaccount = passengerInfo.getPassengerAccount()+rebackaccount;
            passengerInfo.setPassengerAccount(upaccount);
            passengerInfoMapper.updateByPrimaryKeySelective(passengerInfo);

            //增加一条钱包记录
            RechargeRecord rechargeRecord = new RechargeRecord();
            float recharge_account = rebackaccount;
            String option_name = "签改";
            Date recharge_time = new Date();

            rechargeRecord.setPassengerId(passenger_id);
            rechargeRecord.setPassengerName(passenger_name);
            rechargeRecord.setPassengerIdentity(passenger_identity);
            rechargeRecord.setPassengerPhone(passenger_phone);
            rechargeRecord.setRechargeAccount(recharge_account);
            rechargeRecord.setOptionName(option_name);
            rechargeRecord.setRechargeTime(recharge_time);
            //插入一条钱包信息
            rechargeRecordMapper.insertSelective(rechargeRecord);


            //更新机票信息
            //1.改签后增加原有机票的数量
            int upticket_number = orderticketsInfo.getTicketNumber()+orderticket_number;
            orderticketsInfo.setTicketNumber(upticket_number);
            ticketsInfoMapper.updateByPrimaryKeySelective(orderticketsInfo);

            //减少改签时所购买机票的数量
            int upbuyticket_number = ticket_number-purchase_number;
            ticketsInfo.setTicketNumber(upbuyticket_number);
            ticketsInfoMapper.updateByPrimaryKeySelective(ticketsInfo);

            return "改签成功，多余金额已退还至账户";
        }
        else {
            //订单金额小于新订单金额，需要进行支付
            //判断余额是否足以支付剩余需要支付的金额
            //剩余需要支付的金额
            float needcharge = postoderprice - orderprice;
            //获取该乘客的余额
            float passenger_account = passengerInfo.getPassengerAccount();
            //判断用户余额是否足以支付需要交的金额
            if (needcharge > passenger_account) {
                //不足以支付，提示进行充值操作
                return "改签失败，钱包余额不足，请充值";
            }
            else {
                //足以支付
                //更新现有订单状态
                String uporderstatus = "已改签";
                orderInfo.setOrderStatus(uporderstatus);
                orderInfoMapper.updateByPrimaryKeySelective(orderInfo);

                //更新用户金额信息
                float uppasenger_account = passenger_account - needcharge;
                passengerInfo.setPassengerAccount(uppasenger_account);
                passengerInfoMapper.updateByPrimaryKeySelective(passengerInfo);


                //增加一条钱包记录
                RechargeRecord rechargeRecord = new RechargeRecord();
                float recharge_account = -needcharge;
                String option_name = "签改支付";
                Date recharge_time = new Date();
                rechargeRecord.setPassengerId(passenger_id);
                rechargeRecord.setPassengerName(passenger_name);
                rechargeRecord.setPassengerIdentity(passenger_identity);
                rechargeRecord.setPassengerPhone(passenger_phone);
                rechargeRecord.setRechargeAccount(recharge_account);
                rechargeRecord.setOptionName(option_name);
                rechargeRecord.setRechargeTime(recharge_time);
                //插入一条钱包信息
                rechargeRecordMapper.insertSelective(rechargeRecord);

                //更新机票信息
                //1.改签后增加原有机票的数量
                int upticket_number = orderticketsInfo.getTicketNumber()+orderticket_number;
                orderticketsInfo.setTicketNumber(upticket_number);
                ticketsInfoMapper.updateByPrimaryKeySelective(orderticketsInfo);

                //减少改签时所购买机票的数量
                int upbuyticket_number = ticket_number-purchase_number;
                ticketsInfo.setTicketNumber(upbuyticket_number);
                ticketsInfoMapper.updateByPrimaryKeySelective(ticketsInfo);

                return "改签成功，钱包余额充足";
            }
        }
    }

    @Override
    public List<OrderInfo> getAllTicketList() {
        return orderInfoMapper.selectAllTicketlist();
    }

    @Override
    public List<OrderInfo> selectOrder(String searchtext){
        return orderInfoMapper.selectOrder(searchtext);
    }

    @Override
    public List<OrderInfo> searchListBycompanyname(String value) {
        return orderInfoMapper.searchListBycompanyname(value);
    }

    @Override
    public UserRechargeVo getCurrentOrderAccount(String airlineName){
        return orderInfoMapper.getCurrentOrderAccount(airlineName);
    }
}
