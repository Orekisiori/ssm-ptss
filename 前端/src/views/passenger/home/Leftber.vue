<template>
  <div>
    <div class="leftber">
      <el-form ref="form" :model="form" label-width="80px" style=" margin-right: 230px;">
        <el-form-item label="从" style="margin-top: 20px;">
          <el-input placeholder="出发地" v-model="form.ticketStartplace" clearable></el-input>
        </el-form-item>
        <el-form-item label="到">
          <el-input placeholder="目的地" v-model="form.ticketEndplace" clearable></el-input>
        </el-form-item>
        <el-form-item label="由">
          <el-date-picker
            v-model="form.ticketBegintime"
            type="date"
            placeholder="选择日期"
            style="width: 390px;"
            :picker-options="startdateop"
          ></el-date-picker>
        </el-form-item>
        <el-form-item label="至">
          <el-date-picker
            v-model="form.ticketEndtime"
            type="date"
            placeholder="选择日期"
            style="width: 390px;"
            :picker-options="enddateop"
          ></el-date-picker>
        </el-form-item>
        <el-form-item label="人次">
          <el-input-number
            v-model="form.number"
            @change="handleChange"
            :min="1"
            :max="10"
            label="描述文字"
          ></el-input-number>
        </el-form-item>
        <el-form-item style="margin-left: 290px;">
          <el-button type="primary" @click="accuratesearch" round>确定</el-button>
        </el-form-item>
      </el-form>
    </div>

		<!-- 查询结果 -->
    <div>
      <el-dialog title="精确查询" :visible.sync="dialogTableVisible1" width="75%">
        <el-table :data="AccurateList">
					<el-table-column align="center" label="序号" type="index" width="50"></el-table-column>
          <el-table-column align="center" label="出发地" width="80px">
            <template slot-scope="scope">
              <i class="el-icon-position"></i>
              <span style="margin-left: 10px">{{ scope.row.ticketStartplace }}</span>
            </template>
          </el-table-column>
					<el-table-column align="center" label="目的地" width="80px">
            <template slot-scope="scope">
              <i class="el-icon-place"></i>
              <span style="margin-left: 10px">{{ scope.row.ticketEndplace }}</span>
            </template>
          </el-table-column>
					<el-table-column align="center" label="出发时间" width="200px">
            <template slot-scope="scope">
              <i class="el-icon-time"></i>
              <span style="margin-left: 10px">{{ scope.row.ticketBegintime | moment}}</span>
            </template>
          </el-table-column>
					<el-table-column align="center" label="预计到达时间" width="200px">
            <template slot-scope="scope">
              <i class="el-icon-time"></i>
              <span style="margin-left: 10px">{{ scope.row.ticketEndtime | moment}}</span>
            </template>
          </el-table-column>
					<el-table-column align="center" label="所属公司" width="150px">
            <template slot-scope="scope">
              <i class="el-icon-collection-tag"></i>
              <span style="margin-left: 10px">{{ scope.row.airlineName }}</span>
            </template>
          </el-table-column>
					<el-table-column align="center" label="价格" width="80px">
            <template slot-scope="scope">
              <i class="el-icon-money"></i>
              <span style="margin-left: 10px">{{ scope.row.ticketPrice }}</span>
            </template>
          </el-table-column>
					<el-table-column align="center" label="余票数" width="80px">
            <template slot-scope="scope">
              <i class="el-icon-s-data"></i>
              <span style="margin-left: 10px">{{ scope.row.ticketNumber }}</span>
            </template>
          </el-table-column>
          <el-table-column align="center" label="操作">
            <template slot-scope="scope">
              <el-button size="mini" type="danger" @click="showdetial(scope.row.ticketId)">购买</el-button>
            </template>
          </el-table-column>
        </el-table>
      </el-dialog>
    </div>

    <!-- 机票详情 -->
    <div>
      <el-dialog
        title="机票详情"
        :visible.sync="dialogFormVisible"
        :close-on-click-modal="false"
        :close-on-press-escape="false"
        :modal-append-to-body="false"
      >
        <el-form>
          <el-form-item label="所属公司" :label-width="formLabelWidth">
            <el-tag type="success">{{detialTicketInfo.airlineName}}</el-tag>
          </el-form-item>
          <el-form-item label="出发地点" :label-width="formLabelWidth">
            <el-tag type="success">{{detialTicketInfo.ticketStartplace}}</el-tag>
          </el-form-item>
          <el-form-item label="到达地点" :label-width="formLabelWidth">
            <el-tag type="success">{{detialTicketInfo.ticketEndplace}}</el-tag>
          </el-form-item>
          <el-form-item label="出发时间" :label-width="formLabelWidth">
            <el-tag type="success">{{detialTicketInfo.ticketBegintime | moment}}</el-tag>
          </el-form-item>
          <el-form-item label="预计到达时间" :label-width="formLabelWidth">
            <el-tag type="success">{{detialTicketInfo.ticketEndtime | moment}}</el-tag>
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
          <el-form-item label="购买数量" :label-width="formLabelWidth">
            <el-input-number
              v-model="ticketOrderRequest.purchase_number"
              :min="1"
              :max="10"
              size="mini"
            ></el-input-number>
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button @click="dialogFormVisible = false">取 消</el-button>
          <el-button type="primary" @click="PostOrder">确 定</el-button>
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
  name: "LeftBer",
  data() {
    return {
      //订单详情需要数据
      formLabelWidth: "120px",

      form: {
        ticketStartplace: "",
        ticketEndplace: "",
        ticketBegintime: "",
        ticketEndtime: "",
        number: 1
      },
      AccurateList: [],

			//查询数据列表dialog
      dialogTableVisible1: false,

      //显示机票详情
      dialogFormVisible:false,

      //请求当前用户信息
      tokenform: {
        username: localStorage.eleToken
      },

      //保存用户信息
      userform: {},

      //存储机票详情信息表
      detialTicketInfo: {},

      ticketIdform: {
        ticketId: null
      },

      //发送订单请求
      ticketOrderRequest: {
        ticketId: null,
        passengerId: null,
        purchase_number: 1
      },

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
      }
    };
  },
  methods: {
    handleChange(value) {},
    accuratesearch() {
      const that = this;
      this.$axios
        .post(
          "http://localhost:8181/ticket/accurateRequest/"
          +this.form.ticketStartplace
          +"/"+this.form.ticketEndplace
          +"/"+this.form.ticketBegintime
          +"/"+this.form.ticketEndtime
        )
        .then(res => {
          this.AccurateList = res.data;
					this.dialogTableVisible1 = true;
        });
    },

    showdetial(ticketId) {
      const that = this;
      that.ticketIdform.ticketId = ticketId;
      this.$axios
        .post("http://localhost:8181/ticket/getDetial/"+that.ticketIdform.ticketId)
        .then(res => {
          this.detialTicketInfo = res.data;
          this.dialogFormVisible = true;
          this.ticketOrderRequest.ticketId = res.data.ticketId;
        });
      this.$axios
        .post("http://localhost:8181/passenger/current/"+that.tokenform.username)
        .then(res => {
          this.userform = res.data;
          this.ticketOrderRequest.passengerId = res.data.passengerId;
        });
    },

    PostOrder() {
      const that = this;
      this.$axios
        .post(
          "http://localhost:8181/order/createorder/"
          +that.ticketOrderRequest.ticketId+"/"
          +that.ticketOrderRequest.passengerId+"/"
          +that.ticketOrderRequest.purchase_number
        )
        .then(res => {
          if (res.data === "余额充足，支付成功") {
            this.$message({
              message: res.data,
              type: "success"
            });
            this.dialogFormVisible = false;
          } else if (res.data === "机票不足") {
            this.$message({
              message: res.data,
              type: "warning"
            });
          } else if (res.data === "机票已售空") {
            this.$message({
              message: res.data,
              type: "warning"
            });
          } else if (res.data === "余额不足，请充值") {
            this.$message({
              message: res.data,
              type: "warning"
            });
            this.$router.push("/proflie/balance");
          } else {
            this.$message({
              message: res.data,
              type: "error"
            });
          }
        });
    },
  }
};
</script>

<style scoped>
.leftber {
  padding: 0;
  margin: 0 0 0 0;
  width: 50%;
  height: 400px;
  background-color: #5f9ea0;
  align-items: center;
  float: right;
}
</style>
