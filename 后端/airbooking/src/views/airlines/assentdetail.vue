<template>
  <div>
    <div>
      <el-row>
        <el-col :span="24">
          <div class="grid-content">
            <el-tag type="success">订单总金额: {{currentorderaccount}}</el-tag>
            <el-button
              style="margin-left:20px"
              @click="applicateaccount"
              size="mini"
              type="success"
              round
            >申请提现</el-button>
            <el-tag style="margin-left:20px" type="success">账户现有金额: {{account}}</el-tag>
          </div>
        </el-col>
      </el-row>
    </div>
    <div>
      <el-table style="width: 100%" :data="searchresultList" stripe show-overflow-tooltip="true">
        <el-table-column align="center" label="购买日期" width="195px">
          <template slot-scope="scope">
            <i class="el-icon-time"></i>
            <span style="margin-left: 10px">{{ scope.row.purchaseDate | date-format }}</span>
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
        <el-table-column align="center" label="身份证" width="195px">
          <template slot-scope="scope">
            <i class="el-icon-key"></i>
            <span style="margin-left: 10px">{{ scope.row.passengerIdentity }}</span>
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
      </el-table>
    </div>

    <div>
      <el-dialog
        title="申请提现"
        :visible.sync="dialog.show"
        :close-on-click-modal="false"
        :close-on-press-escape="false"
        :modal-append-to-body="false"
      >
        <div class="form">
          <el-form
            ref="requestaccount"
            :model="requestaccount"
            :rules="form_rules"
            label-width="80px"
          >
            <el-form-item label="申请金额" prop="requestaccount">
              <el-input type="requestAccount" v-model="requestaccount.requestAccount"></el-input>
            </el-form-item>
            <el-form-item style="text-align:center">
              <el-button type="primary" @click="onSubmit('requestaccount')" round>提现</el-button>
              <el-button type="info" @click="dialog.show = false" round>取消</el-button>
            </el-form-item>
          </el-form>
        </div>
      </el-dialog>
    </div>
  </div>
</template>

<script>
export default {
  name: "AssentDetail",
  data() {
    return {
      searchform: {
        value: localStorage.eleToken
      },
      tokenform: {
        searchtext: localStorage.eleToken
      },
      getcurrntOrderaccont: {
        airlineName: localStorage.eleToken
      },
      searchresultList: [],
      account: 0,
      currentorderaccount: 0,
      dialog: {
        show: false
      },
      requestaccount: {
        airlineId: null,
        airlineName: null,
        airlineIdentity: null,
        requestAccount: 0
      },
      form_rules: {
        requestAccount: [
          { required: true, message: "金额不能为空", trigger: "blur" },
          {
            pattern: /^([1-9][0-9]*)+(.[0-9]{1,2})?$/,
            message: "请输入正确的充值金额",
            trigger: "blur"
          }
        ]
      }
    };
  },
  created() {
    this.getcurrentOrder();
    this.getairlineId();
    this.getcurrntOrderacconts();
  },
  methods: {
    getcurrentOrder() {
      this.$axios
        .post(
          "http://localhost:8087/orders/searchListBycompanyname",
          this.searchform
        )
        .then(res => {
          this.searchresultList = res.data;
          this.searchresultList.sort((a,b) => b.purchaseDate - a.purchaseDate)
        });
    },

    //获取当前航空公司信息
    getairlineId() {
      this.$axios
        .post("http://localhost:8087/airlineInfo/searchByName", this.tokenform)
        .then(res => {
          this.account = res.data[0].airlineAccount;
          this.requestaccount.airlineId = res.data[0].airlineId;
          this.requestaccount.airlineName = res.data[0].airlineName;
          this.requestaccount.airlineIdentity = res.data[0].airlineIdentity;
        });
    },

    //获取当前公司订单收益
    getcurrntOrderacconts() {
      this.$axios
        .post(
          "http://localhost:8087/orders/getCurrentOrderAccount",
          this.getcurrntOrderaccont
        )
        .then(res => {
          this.currentorderaccount = res.data.result;
        });
    },
    //申请提现dialog
    applicateaccount() {
      this.dialog.show = true;
    },
    //提交申请
    onSubmit(requestaccount) {
      this.$axios
        .post(
          "http://localhost:8087/applicate/postapplicateaccount",
          this.requestaccount
        )
        .then(res => {
          if (res.data == "提现申请成功") {
            this.$message({
              message: res.data,
              type: "success"
            });
            this.dialog.show = false;
          } else {
            this.$message({
              message: "提现申请失败",
              type: "success"
            });
          }
        });
    }
  }
};
</script>

<style scoped>
.grid-content {
  border-radius: 4px;
  min-height: 36px;
}
</style>
