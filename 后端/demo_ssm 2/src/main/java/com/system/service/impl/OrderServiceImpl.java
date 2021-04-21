package com.system.service.impl;

import com.system.domain.*;
import com.system.domain.dto.crateOrderDto;
import com.system.domain.dto.orderDto;
import com.system.domain.dto.orderFlowInfoDto;
import com.system.domain.dto.resultDto;
import com.system.domain.vo.orderVo;
import com.system.mapper.TOrderMapper;
import com.system.service.*;
import com.system.utils.MyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Service
public class OrderServiceImpl  implements OrderService {
    @Autowired
    private TOrderMapper tOrderMapper;

    @Autowired
    private OrderInfoService orderInfoService;

    @Autowired
    private FlowinfoService flowinfoService;

    @Autowired
    private CartService cartService;

    @Autowired
    private FlowSoldService flowSoldService;


    /**
     * 订单表插入数据，订单详情表插入数据，已售商品表插入数据
     * @param orderVo
     */
    @Transactional(rollbackFor = Exception.class)
    public void addOrder(orderVo orderVo) {
        //从OrderVo获得order对象和所有商品的集合
        TOrder order = orderVo.gettOrder();
        List<TFlowInfo> flowInfoList = orderVo.getFlowInfoList();

        //将order存到订单表中
        tOrderMapper.insert(order);
        //将数据存到订单详情表中,迭代器获取每个鲜花的信息，然后存到List<TForderInfo>和List<TFlowSold>中
        Iterator<TFlowInfo> infoIterator = flowInfoList.iterator();
        List<TOrderInfo> orderInfoList = new ArrayList<TOrderInfo>();
        List<TFlowSold> tFlowSoldList = new ArrayList<TFlowSold>();

        while (infoIterator.hasNext()){
            //指向下一个鲜花
            TFlowInfo tFlowInfo = infoIterator.next();

            //将订单id和鲜花id存到订单详情表中
            TOrderInfo tOrderInfo = new TOrderInfo();
            tOrderInfo.setOrderId(order.getOrderId());
            tOrderInfo.setfId(tFlowInfo.getfId());
            TCart tCart=cartService.getfNulberByfId(tFlowInfo.getfId());
            tOrderInfo.setfNumber(tCart.getfNumber());

            //将商品信息存到已售商品表中
            TFlowSold tFlowSold = new TFlowSold();
            tFlowSold.setfName(tFlowInfo.getfName());
            tFlowSold.setfId(tFlowInfo.getfId());
            tFlowSold.setfNumber(tCart.getfNumber());
            tFlowSold.setfPrice(order.getOrderPrice());
            tFlowSold.setCrateTime(new Date());
            tFlowSold.setUpdateTime(new Date());

            orderInfoList.add(tOrderInfo);
            tFlowSoldList.add(tFlowSold);
        }


        //list是商品详细信息,插入list到订单详情表
        //事务B的回滚不会影响事务A,参数为NESTED
        try {
            orderInfoService.addOrderInfos(orderInfoList);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //list是商品详细信息,插入list到已售鲜花表中
        //事务B的回滚不会影响事务A,参数为NESTED
        try {
            flowSoldService.addFlowSold(tFlowSoldList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 重载方法，接受dto数据，转换为vo数据
     * @param orderDto
     * @return
     */
    public resultDto addOrder(orderDto orderDto) {
        orderVo orderVo = new orderVo();
        TOrder tOrder = new TOrder();
        resultDto resultDto = new resultDto();

        try{
            //封装order数据===orderid根据日期自动生成
            tOrder.setOrderId(MyUtils.getCurrentTimeForId());
            tOrder.setUserId(orderDto.getUserId());
            tOrder.setOrderUser(orderDto.getOrderUser());
            tOrder.setOrderTel(orderDto.getOrderTel());
            tOrder.setOrderAddr(orderDto.getOrderAddr());
            tOrder.setOrderStatic("0");
            tOrder.setOrderPrice(orderDto.getOrderPrice());
            tOrder.setCreateTime(new Date());
            tOrder.setUpdateTime(new Date());

            //根据fIds获取到鲜花信息集合
            List<Integer> fIds = orderDto.getfIds();
            List<TFlowInfo> list = flowinfoService.selectByfIds(fIds);
            //封装orderVo的数据
            orderVo.setFlowInfoList(list);
            orderVo.settOrder(tOrder);
            addOrder(orderVo);

            //下单成功返回信息
            resultDto.setResult(true);
            resultDto.setMessage("下单成功");
        }
        catch (Exception e) {
            e.printStackTrace();
            //后端不正常插入数据，捕获异常，下单失败返回信息
            resultDto.setResult(false);
            resultDto.setMessage("下单失败,请检查后端");
        }
        return resultDto;
    }

    /**
     * 获取当前用户所有订单
     * @param userId
     * @return
     */
    public List<crateOrderDto> getList(Integer userId) {
        //根据userid查询所有订单信息
        List<TOrder> orderList=tOrderMapper.selectByUserId(userId);

        List<crateOrderDto> cods = new ArrayList<crateOrderDto>();
        //遍历所有订单信息取出对应的的数据(订单时间，订单id，订单价格,订单状态)
        orderList.forEach(order ->{
            crateOrderDto cod = new crateOrderDto();
            cod.setCrateTime(order.getCreateTime());
            cod.setOrderId(order.getOrderId());
            cod.setOrderPrice(order.getOrderPrice());
            cod.setOrderStatic(order.getOrderStatic());
            cod.setOrderUser(order.getOrderUser());
            cod.setOrderTel(order.getOrderTel());
            cod.setOrderAddr(order.getOrderAddr());
            //将所有订单数据存到cods中
            cods.add(cod);
        });
        //遍历cods
        //根据订单编号去订单详情表找对应的商品id和商品数量
        // 根据商品id去商品表中查询商品信息
        cods.forEach(cod ->{
            //根据订单编号，去订单详情表中获取所有商品id及其数量
            List<TOrderInfo> orderInfoList=orderInfoService.getOrderInfoByOrderId(cod.getOrderId());
            //遍历orderInfoList获取商品id,获取数量
            List<orderFlowInfoDto> ofos = new ArrayList<>();
            orderInfoList.forEach(orderinfo ->{
                //通过商品id封装orderFlowInfoDto
                orderFlowInfoDto ofo = getOderFlowInfoDto(orderinfo.getfId());
                ofo.setfNumber(orderinfo.getfNumber());
                ofo.setfId(orderinfo.getfId());
                //将遍历的数量放到ofos集合中
                ofos.add(ofo);
            });
            //将商品信息存到crateOrderDto中
            cod.setFlowInfoList(ofos);
        });
        return cods;
    }

    /**
     * 支付成功更新订单状态
     * @param tOrder
     */
    @Override
    public void updateStatic(TOrder tOrder) {
        tOrderMapper.updateStatic(tOrder);
    }

    /**
     * 根据商品id,获取商品信息
     * @param fId
     * @return
     */
    private orderFlowInfoDto getOderFlowInfoDto(Integer fId) {
        orderFlowInfoDto orderFlowInfoDto = new orderFlowInfoDto();

        TFlowInfo flowInfo = flowinfoService.selectByPrimaryKey(fId);

        orderFlowInfoDto.setfName(flowInfo.getfName());
        orderFlowInfoDto.setfPrice(flowInfo.getfPrice());

        return orderFlowInfoDto;
    }
}
