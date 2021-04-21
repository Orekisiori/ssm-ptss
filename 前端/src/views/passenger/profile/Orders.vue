<template>
  <div>
    <div>
      <el-table style="width: 100%" :data="allTableDate" stripe show-overflow-tooltip="true">
        <el-table-column align="center" label="购买日期" width="195px">
          <template slot-scope="scope">
            <i class="el-icon-time"></i>
            <span style="margin-left: 10px">{{ scope.row.purchaseDate | moment }}</span>
          </template>
        </el-table-column>
        <el-table-column align="center" label="机票价格" width="width">
          <template slot-scope="scope">
            <i class="el-icon-s-finance"></i>
            <span style="margin-left: 10px">{{ scope.row.ticketPrice }}</span>
          </template>
        </el-table-column>
        <el-table-column align="center" label="购票数量" width="width">
          <template slot-scope="scope">
            <i class="el-icon-s-data"></i>
            <span style="margin-left: 10px">{{ scope.row.purchaseNumber }}</span>
          </template>
        </el-table-column>
        <el-table-column align="center" label="出发地" width="width">
          <template slot-scope="scope">
            <i class="el-icon-position"></i>
            <span style="margin-left: 10px">{{ scope.row.startplace }}</span>
          </template>
        </el-table-column>
        <el-table-column align="center" label="目的地" width="width">
          <template slot-scope="scope">
            <i class="el-icon-place"></i>
            <span style="margin-left: 10px">{{ scope.row.endplace }}</span>
          </template>
        </el-table-column>
        <el-table-column align="center" label="乘机人" width="80px">
          <template slot-scope="scope">
            <i class="el-icon-user"></i>
            <span style="margin-left: 10px">{{ scope.row.passengerName }}</span>
          </template>
        </el-table-column>
        <el-table-column align="center" label="机舱/座位" width="80px">
          <template slot-scope="scope">
            <i class="el-icon-s-check"></i>
            <span style="margin-left: 10px">{{ scope.row.seat }}</span>
          </template>
        </el-table-column>
        <el-table-column align="center" label="手机号" width="150px">
          <template slot-scope="scope">
            <i class="el-icon-mobile-phone"></i>
            <span style="margin-left: 10px">{{ scope.row.passengerPhone }}</span>
          </template>
        </el-table-column>
        <el-table-column align="center" label="总金额" width="width">
          <template slot-scope="scope">
            <i class="el-icon-money"></i>
            <span style="margin-left: 10px">{{ scope.row.orderPrice }}</span>
          </template>
        </el-table-column>
        <el-table-column align="center" label="订单状态" width="110px">
          <template slot-scope="scope">
            <i class="el-icon-guide"></i>
            <span style="margin-left: 10px">{{ scope.row.orderStatus }}</span>
          </template>
        </el-table-column>
        <el-table-column align="center" label="操作" width="150px">
          <template slot-scope="scope">
            <el-button size="mini" @click="Refund(scope.$index, scope.row)">退票</el-button>
            <el-button size="mini" type="danger" @click="Change(scope.$index, scope.row)">改签</el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>

    <!-- 展示当前订单信息已供查询是否有可改签的机票，点击确定后展示查询到的机票列表 -->
    <div>
      <el-dialog
        title="改签处理"
        :visible.sync="dialogFormVisible"
        :close-on-click-modal="false"
        :close-on-press-escape="false"
        :modal-append-to-body="false"
      >
        <el-form>
          <el-form-item label="出发地" :label-width="formLabelWidth">
            <el-tag type="info">{{dataOne.startplace}}</el-tag>
          </el-form-item>
          <el-form-item label="目的地" :label-width="formLabelWidth">
            <el-tag type="info">{{dataOne.endplace}}</el-tag>
          </el-form-item>
          <el-form-item label="由" :label-width="formLabelWidth">
            <el-date-picker
              v-model="form.ticketBegintime"
              type="date"
              placeholder="选择日期"
              style="width: 250px;"
              :picker-options="startdateop"
            ></el-date-picker>
          </el-form-item>
          <el-form-item label="至" :label-width="formLabelWidth">
            <el-date-picker
              v-model="form.ticketEndtime"
              type="date"
              placeholder="选择日期"
              style="width: 250px;"
              :picker-options="enddateop"
            ></el-date-picker>
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button @click="dialogFormVisible = false">取 消</el-button>
          <el-button type="primary" @click="postChange">确 定</el-button>
        </div>
      </el-dialog>
    </div>

    <!-- 展示查询到的机票列表 -->
    <div>
      <el-dialog title="可改签机票列表" :visible.sync="ticketListshow" width="75%">
        <el-table :data="Changeavialable" style="width: 100%">
          <af-table-column align="center" label="序号" type="index" width="50"></af-table-column>
          <af-table-column align="center" label="出发地" width="80px">
            <template slot-scope="scope">
              <i class="el-icon-position"></i>
              <span style="margin-left: 10px">{{ scope.row.ticketStartplace}}</span>
            </template>
          </af-table-column>
          <af-table-column align="center" label="目的地" width="80px">
            <template slot-scope="scope">
              <i class="el-icon-place"></i>
              <span style="margin-left: 10px">{{ scope.row.ticketEndplace}}</span>
            </template>
          </af-table-column>
          <af-table-column align="center" label="出发时间" width="195px">
            <template slot-scope="scope">
              <i class="el-icon-time"></i>
              <span style="margin-left: 10px">{{ scope.row.ticketBegintime | moment}}</span>
            </template>
          </af-table-column>
          <af-table-column align="center" label="预计到达时间" width="190px">
            <template slot-scope="scope">
              <i class="el-icon-time"></i>
              <span style="margin-left: 10px">{{ scope.row.ticketEndtime | moment}}</span>
            </template>
          </af-table-column>
          <af-table-column align="center" label="所属公司" width="150px">
            <template slot-scope="scope">
              <i class="el-icon-collection-tag"></i>
              <span style="margin-left: 10px">{{ scope.row.airlineName}}</span>
            </template>
          </af-table-column>
          <af-table-column align="center" label="价格" width="80px">
            <template slot-scope="scope">
              <i class="el-icon-money"></i>
              <span style="margin-left: 10px">{{ scope.row.ticketPrice}}</span>
            </template>
          </af-table-column>
          <af-table-column align="center" label="余票数" width="80px">
            <template slot-scope="scope">
              <i class="el-icon-s-data"></i>
              <span style="margin-left: 10px">{{ scope.row.ticketNumber}}</span>
            </template>
          </af-table-column>
          <el-table-column label="操作">
            <template slot-scope="scope">
              <el-button size="mini" type="danger" @click="handle(scope.row)">改签</el-button>
            </template>
          </el-table-column>
        </el-table>
      </el-dialog>
    </div>

    <!-- 机票详情 -->
    <div>
      <el-dialog
        title="机票详情"
        :visible.sync="orderdetail"
        :close-on-click-modal="false"
        :close-on-press-escape="false"
        :modal-append-to-body="false"
      >
        <el-form>
          <el-form-item label="所属公司" :label-width="formLabelWidth">
            <el-tag type="success">{{showdetailinfo.airlineName}}</el-tag>
          </el-form-item>
          <el-form-item label="出发地点" :label-width="formLabelWidth">
            <el-tag type="success">{{showdetailinfo.ticketStartplace}}</el-tag>
          </el-form-item>
          <el-form-item label="到达地点" :label-width="formLabelWidth">
            <el-tag type="success">{{showdetailinfo.ticketEndplace}}</el-tag>
          </el-form-item>
          <el-form-item label="出发时间" :label-width="formLabelWidth">
            <el-tag type="success">{{showdetailinfo.ticketBegintime | moment}}</el-tag>
          </el-form-item>
          <el-form-item label="预计到达时间" :label-width="formLabelWidth">
            <el-tag type="success">{{showdetailinfo.ticketEndtime | moment}}</el-tag>
          </el-form-item>
          <el-form-item label="乘机人" :label-width="formLabelWidth">
            <el-tag type="success">{{userform.passengerName}}</el-tag>
          </el-form-item>
          <el-form-item label="身份证号" :label-width="formLabelWidth">
            <el-tag type="success">{{userform.passengerIdentity}}</el-tag>
          </el-form-item>
          <el-form-item label="手机号" :label-width="formLabelWidth">
            <el-tag type="success">{{userform.passengerPhone}}</el-tag>
          </el-form-item>
<!--          <el-form-item label="改签数量" :label-width="formLabelWidth">
            <el-input-number
              v-model="ticketOrderRequest.purchase_number"
              :min="1"
              :max="10"
              size="mini"
            ></el-input-number>
          </el-form-item>-->
          <el-form-item label="机票售价" :label-width="formLabelWidth">
            <el-tag >{{dataOne.orderPrice}}￥ → {{showdetailinfo.ticketPrice}}￥</el-tag>
          </el-form-item>

        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button @click="orderdetail = false">取 消</el-button>
          <el-button type="primary" @click="requestchange">确 定</el-button>
        </div>
      </el-dialog>
    </div>
  </div>
</template>

<script>
//获取前一天的日期
let time = new Date().getTime() - 24 * 60 * 60 * 1000;
let yesterday = new Date(time);

export default {
  name: "Orders",
  data() {
    return {
      //日期选择器
      //判断所选日期是否大于前一天的日期来决定是否可选
      startdateop: {
        disabledDate: time => {
          return time.getTime() < yesterday;
        }
      },
      //判断选择的日期是否大于或等于开始日期
      enddateop: {
        disabledDate: time => {
          return time.getTime() < this.form.ticketBegintime;
        }
      },
      form: {
        ticketBegintime: "",
        ticketEndtime: ""
      },

      //改签
      dialogFormVisible: false,
      formLabelWidth: "120px",
      //提交改签信息，查看是否有相应机票
      postChangeinfo: {
        ticketStartplace: "",
        ticketEndplace: "",
        ticketBegintime: "",
        ticketEndtime: ""
      },

      //改签时查询到可以进行改签的机票列表
      ticketListshow: false,
      Changeavialable: [],
      tokenform: {
        username: localStorage.eleToken
      },
      id: {
        passengerId: 0
      },
      tableDate: [],
      allTableDate: [],
      filterTableData: [],
      dataOne: {},

      //发起订单请求
      orderdetail: false,
      userform: {},
      ticketOrderRequest: {
        orderId:null,
        passengerId: null,
        purchase_number: 1,
        //改签购买时的机票id
        ticketId: null,
      },

      //保存显示订单时的信息
      showdetailinfo: {}
    };
  },
  created() {
    this.getlsits();
  },
  methods: {
    getlsits() {
      const that = this;
      new Promise((resolve, reject) => {
        resolve(
          this.$axios
            .post("http://localhost:8181/passenger/current/"+that.tokenform.username)
            .then(res => {
              this.id.passengerId = res.data.passengerId;
              this.ticketOrderRequest.passengerId = res.data.passengerId;
              this.userform = res.data;
              return new Promise((resolve, reject) => {
                resolve(
                  this.$axios
                    .post("http://localhost:8181/order/getList/"+that.ticketOrderRequest.passengerId)
                    .then(res => {
                      console.log(res.data);
                      this.allTableDate = res.data;
                      this.allTableDate.sort((a,b) => b.orderId - a.orderId);
                    })
                );
              });
            })
        );
      });
    },

    Refund(index, row) {
      this.$axios.post("http://localhost:8181/order/refund", row).then(res => {
        if (res.data === "已退票，请勿重复操作") {
          this.$message({
            message: res.data,
            type: "warning"
          });
        } else if (res.data === "退票成功") {
          this.$message({
            message: res.data,
            type: "success"
          });
          this.getlsits();
        } else {
          this.$message({
            message: "未知错误",
            type: "error"
          });
        }
      });
    },

    Change(index, row) {
      if (row.orderStatus === "已改签") {
        this.$message({
          message: "您已改签，不可再次改签",
          type: "warning"
        });
      } else if (row.orderStatus === "已退票") {
        this.$message({
          message: "您已退票，不可改签",
          type: "warning"
        });
      } else {
        //存储原订单信息
        this.dataOne = row;
        this.ticketOrderRequest.orderId = row.orderId;
        //开启改签会话
        this.dialogFormVisible = true;

      }
    },

    postChange() {
      this.postChangeinfo.ticketStartplace = this.dataOne.startplace;
      this.postChangeinfo.ticketEndplace = this.dataOne.endplace;
      this.postChangeinfo.ticketBegintime = this.form.ticketBegintime;
      this.postChangeinfo.ticketEndtime = this.form.ticketEndtime;
      this.$axios
        .post(
          "http://localhost:8181/ticket/accurateRequest/"
          +this.postChangeinfo.ticketStartplace +"/"
          +this.postChangeinfo.ticketEndplace +"/"
          +this.postChangeinfo.ticketBegintime +"/"
          +this.postChangeinfo.ticketEndtime
        )
        .then(res => {
          // 没有可以改签的机票，提示退票
          if (res.data.length === 0) {
            this.$message({
              message: "所选时段内无可供改签的机票，您可以选择其他时段或申请退票",
              type: "warning"
            });
            this.dialogFormVisible = false;
          } else {
            //有机票,将数据赋值给Changeavialable以显示机票列表
            this.Changeavialable = res.data;
            this.ticketOrderRequest.ticketId = res.data.ticketId;
            this.dialogFormVisible = false;
            this.ticketListshow = true;
          }
        });
    },

    handle(row) {
      this.showdetailinfo = row;
      this.ticketListshow = false;
      this.ticketOrderRequest.ticketId = row.ticketId;
      this.orderdetail = true;
    },

    requestchange() {
      //改签时传入当前用户id，当前订单id，当前机票id和购买数量,这些数据都保存在ticketOrderRequest
      this.$axios
        .post("http://localhost:8181/order/changeOrder/"
          +this.ticketOrderRequest.orderId+"/"
          +this.ticketOrderRequest.passengerId+"/"
          +this.ticketOrderRequest.purchase_number+"/"
          +this.ticketOrderRequest.ticketId)
        .then(res => {
          if(res.data == "改签成功，价格相同，无需再次付款") {
            this.$message({
              message: res.data,
              type: "success"
            });
          }
          else if(res.data === "改签成功，多余金额已退还至账户") {
            this.$message({
              message: res.data,
              type: "success"
            });
          }
          else if(res.data === "改签失败，钱包余额不足，请充值") {
            this.$message({
              message: res.data,
              type: "warning"
            });
          }
          else if(res.data === "改签成功，钱包余额充足") {
            this.$message({
              message: "支付成功，已改签",
              type: "success"
            });
          }
          else {
            this.$message({
              message: "未知错误",
              type: "error"
            });
          }
        })
    }
  }
};
</script>

<style scoped>

</style>
