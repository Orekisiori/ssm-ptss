<template id="headers">
  <div>
    <el-row>
      <el-col :span="6">
        <div class="grid-content"></div>
      </el-col>
      <el-col :span="9">
        <div class="grid-content bg-purple-light">
          <el-form :inline="true" style="height: 36px;text-align: center">
            <el-form-item>
              <el-input v-model="postsearchinfo.ticketStartplace" placeholder style="width: 125px;"></el-input>
            </el-form-item>
            <i class="el-icon-refresh" style="margin-top:12px"></i>
            <el-form-item>
              <el-input v-model="postsearchinfo.ticketEndplace" placeholder style="width: 125px;"></el-input>
            </el-form-item>
            <el-form-item>
              <el-button type="primary" @click="onSubmit" round>搜索</el-button>
            </el-form-item>
          </el-form>
        </div>
      </el-col>
      <el-col :span="9">
        <div class="grid-content">
          <el-row>
            <el-button round size="small" @click="backhome">首页</el-button>
            <el-button type="primary" round size="small" @click="mybalance">我的余额</el-button>
            <el-button type="success" round size="small" @click="profilebtn">个人中心</el-button>
            <el-button type="info" round size="small" @click="odersbtn">我的订单</el-button>
            <el-button type="warning" round size="small" @click="updatepwd">修改密码</el-button>
            <el-button type="danger" round size="small" @click="logout">退出</el-button>
            <el-avatar class="avatar" icon="el-icon-user-solid" size="small"></el-avatar>
          </el-row>
        </div>
      </el-col>
    </el-row>

    <!-- 查询机票dialog -->
    <div>
      <el-dialog
        title="机票详情"
        :visible.sync="dialogFormVisible1"
        :close-on-click-modal="false"
        :close-on-press-escape="false"
        :modal-append-to-body="false"
        width="75%"
      >
        <el-table :data="requestTicket" style="width: 100%;" stripe show-overflow-tooltip="true">
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
          <af-table-column align="center" label="预计到达时间" width="195px">
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
          <af-table-column align="center" label="操作">
            <template slot-scope="scope">
              <el-button size="mini" type="danger" @click="showdetial(scope.row.ticketId)">购买</el-button>
            </template>
          </af-table-column>
        </el-table>
      </el-dialog>
    </div>

    <!-- 订单详情dialog -->
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
          <el-button @click="dialogFormVisible=false">取 消</el-button>
          <el-button type="primary" @click="PostOrder">确 定</el-button>
        </div>
      </el-dialog>
    </div>

    <!-- 修改密码的dialog -->
    <div>
      <el-dialog title="修改密码" :visible.sync="updatepwddialog" width="30%" center>
        <el-form
          :model="ruleForm"
          status-icon
          :rules="rules"
          ref="ruleForm"
          label-width="100px"
          class="demo-ruleForm"
        >
          <el-form-item label="原密码" prop="orignpass">
            <el-input type="password" v-model="ruleForm.orignpass" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="密码" prop="pass">
            <el-input type="password" v-model="ruleForm.pass" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="确认密码" prop="checkPass">
            <el-input type="password" v-model="ruleForm.checkPass" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item>
            <el-button type="primary" @click="submitForm('ruleForm')">提交</el-button>
            <el-button @click="resetForm('ruleForm')">重置</el-button>
          </el-form-item>
        </el-form>
      </el-dialog>
    </div>
  </div>
</template>

<script>
export default {
  name: "Header",
  data() {
    //验证两次密码是否一样
    const validatePass = (rule, value, callback) => {
      if (value !== this.ruleForm.pass) {
        callback(new Error("两次输入密码不一致!"));
      } else {
        callback();
      }
    };

    return {
      num: 1,
      postsearchinfo: {
        ticketStartplace: null,
        ticketEndplace: null
      },
      dialog: {
        show: false
      },
      requestTicket: [],
      dialogFormVisible: false,
      dialogFormVisible1: false,
      formLabelWidth: "120px",
      ticketIdform: {
        ticketId: null
      },
      detialTicketInfo: {},
      userform: {
        passengerName: '',
        passengerIdentity:'',
        passengerGender: '',
        passengerPhone: ''
      },
      tokenform: {
        username: localStorage.eleToken
      },
      ticketOrderRequest: {
        ticketId: null,
        passengerId: null,
        purchase_number: null
      },
      updatepwddialog: false,
      ruleForm: {
        passengerId: null,
        orignpass: "",
        pass: "",
        checkPass: ""
      },
      rules: {
        orignpass: [
          {
            required: true,
            message: "原始密码不能为空",
            trigger: "blur"
          },
          {
            min: 6,
            max: 20,
            message: "长度在6-20之间",
            trigger: "blur"
          }
        ],
        pass: [
          {
            required: true,
            message: "新密码不能为空",
            trigger: "blur"
          },
          {
            min: 6,
            max: 20,
            message: "长度在6-20之间",
            trigger: "blur"
          }
        ],
        checkPass: [
          {
            required: true,
            message: "确认新密码不能为空",
            trigger: "blur"
          },
          {
            min: 6,
            max: 20,
            message: "长度在6-20之间",
            trigger: "blur"
          },
          {
            validator: validatePass,
            trigger: "blur"
          }
        ]
      }
    };
  },
  methods: {
    onSubmit() {
      this.$axios
        .post(
          "http://localhost:8181/ticket/requestTicket/"+this.postsearchinfo.ticketStartplace+"/"+this.postsearchinfo.ticketEndplace
        )
        .then(res => {
          this.requestTicket = res.data;
          console.log(res.data);
          this.dialogFormVisible1 = true;
        });
    },

    showdetial(ticketId) {
      const that = this;
      that.ticketIdform.ticketId = ticketId;
      this.$axios
        .post("http://localhost:8181/ticket/getDetial/"+that.ticketIdform.ticketId)
        .then(res => {
          console.log(res.date);
          this.detialTicketInfo = res.data;
          this.dialogFormVisible1 = false;
          this.dialogFormVisible = true;
          this.ticketOrderRequest.ticketId = res.data.ticketId;
        });
      console.log(that.tokenform.username);
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
          "http://localhost:8181/order/createorder/"+that.ticketOrderRequest.ticketId+"/"+that.ticketOrderRequest.passengerId+"/"+that.ticketOrderRequest.purchase_number
        )
        .then(res => {
          if (res.data === "余额充足，支付成功") {
            this.$message({
              message: res.data,
              type: "success"
            });
            this.dialogFormVisible = false;
            this.dialogFormVisible1 = false;
          } else if (res.data === "机票不足") {
            this.$message({
              message: res.data,
              type: "success"
            });
          } else if (res.data === "机票已售空") {
            this.$message({
              message: res.data,
              type: "success"
            });
          } else if (res.data === "余额不足，请充值") {
            this.$message({
              message: res.data,
              type: "success"
            });
            this.$router.push("/proflie/balance");
          } else {
            this.$message({
              message: res.data,
              type: "success"
            });
          }
        });
    },

    // handleDelete(index, row) {
    //   console.log(index, row);
    // },
    profilebtn() {
      this.$router.push("/profile/profiles");
    },
    backhome() {
      if (this.$router.history.current.path !== "/home")
        this.$router.push("/home");
      else this.$router.go(0);
    },
    mybalance() {
      this.$router.push("/profile/balance");
    },
    odersbtn() {
      this.$router.push("/profile/orders");
    },
    logout() {
      // 清除token
      localStorage.removeItem("eleToken");
      this.$store.dispatch("clearCurrentState");
      // 页面跳转
      this.$router.push("/login");
    },
    //修改密码dialog
    updatepwd() {
      this.updatepwddialog = true;
    },

    //提交修改密码
    submitForm(formName) {
      this.$refs[formName].validate(valid => {
        if (valid) {//unuser:reject
          new Promise((resolve) => {
            resolve(
              this.$axios
                .post(
                  "http://localhost:8181/passenger/current/"+this.tokenform.username
                )
                .then(res => {
                  this.userform = res.data;
                  this.ruleForm.passengerId = res.data.passengerId;
                  return new Promise(() => {//unused:resolve, reject
                    this.$axios
                      .post(
                        "http://localhost:8181/passenger/updatepwd/"
                        +this.ruleForm.passengerId+"/"
                        +this.ruleForm.orignpass+"/"
                        +this.ruleForm.checkPass
                      )
                      .then(res => {
                        if (res.data === "修改成功") {
                          this.$message({
                            message: res.data,
                            type: "success"
                          });
                          // 清除token,重新登录
                          localStorage.removeItem("eleToken");
                          this.$store.dispatch("clearCurrentState");
                          // 页面跳转
                          this.$router.push("/login");
                        } else if (res.data === "原密码错误") {
                          this.$message({
                            message: res.data,
                            type: "error"
                          });
                        } else {
                          this.$message({
                            message: "未知错误",
                            type: "error"
                          });
                        }
                      });
                  });
                })
            );
          });
        } else {
          this.$message({
            message: "提交失败",
            type: "error"
          });
          return false;
        }
      });
    },
    //重置输入
    resetForm(formName) {
      this.$refs[formName].resetFields();
    }
  }
};
</script>

<style scoped>
.el-row {
  margin-bottom: 5px;
  margin-top: 5px;
}

.el-col {
  border-radius: 4px;
}

.grid-content {
  border-radius: 4px;
  min-height: 36px;
  margin-bottom: 5px;
}

.avatar {
  padding: 0;
  margin: 0;
  float: right;
}
</style>
