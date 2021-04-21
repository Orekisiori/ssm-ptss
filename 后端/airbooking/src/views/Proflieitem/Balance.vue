<template>
  <div class="balancewapper">
    <div class="balance">
      <div class="dashed"></div>
      <div class="show">
        <span class="showdesc">我的余额(¥)</span>
        <h1>{{passenger_account}}</h1>
        <div class="btn">
          <el-button type="info" @click="handleCharge" round size="mini">在线充值</el-button>
        </div>
      </div>
    </div>
    <div style="margin-top:10px">
      <el-table :data="tableData" style="width: 100%;" stripe show-overflow-tooltip="true">
        <af-table-column align="center" label="充值日期" width="190">
          <template slot-scope="scope">
            <i class="el-icon-time"></i>
            <span style="margin-left: 10px">{{ scope.row.rechargeTime | date-format}}</span>
          </template>
        </af-table-column>
        <af-table-column align="center" label="充值人" width="150">
          <template slot-scope="scope">
            <i class="el-icon-user"></i>
            <span style="margin-left: 10px">{{ scope.row.passengerName }}</span>
          </template>
        </af-table-column>
        <af-table-column align="center" label="身份证号" width="200">
          <template slot-scope="scope">
            <i class="el-icon-key"></i>
            <span style="margin-left: 10px">{{ scope.row.passengerIdentity }}</span>
          </template>
        </af-table-column>
        <af-table-column align="center" label="手机号" width="180">
          <template slot-scope="scope">
            <i class="el-icon-mobile-phone"></i>
            <span style="margin-left: 10px">{{ scope.row.passengerPhone }}</span>
          </template>
        </af-table-column>
        <af-table-column align="center" label="资金流水" width="180">
          <template slot-scope="scope">
            <i class="el-icon-receiving"></i>
            <span style="margin-left: 10px;color:green">{{ scope.row.rechargeAccount }}</span>
          </template>
        </af-table-column>
        <af-table-column align="center" label="操作类型" width="180">
          <template slot-scope="scope">
            <i class="el-icon-set-up"></i>
            <span style="margin-left: 10px">{{ scope.row.optionName }}</span>
          </template>
        </af-table-column>
        <!-- <af-table-column align="center" label="操作">
          <template slot-scope="scope">
            <el-button size="mini" type="danger" @click="handleDelete(scope.$index, scope.row)">删除</el-button>
          </template>
        </af-table-column> -->
      </el-table>
    </div>

    <el-dialog
      title="账户充值"
      :visible.sync="dialog.show"
      :close-on-click-modal="false"
      :close-on-press-escape="false"
      :modal-append-to-body="false"
    >
      <div class="form">
        <el-form ref="rechargedata" :model="rechargedata" :rules="form_rules" label-width="80px">
          <el-form-item label="充值金额" prop="rechargedata">
            <el-input type="rechargedata" v-model="rechargedata.rechargedata"></el-input>
          </el-form-item>
          <el-form-item style="text-align:center">
            <el-button type="primary" @click="onSubmit('rechargedata')" round>充值</el-button>
            <el-button type="info" @click="dialog.show = false" round>取消</el-button>
          </el-form-item>
        </el-form>
      </div>
    </el-dialog>
  </div>
</template>

<script>
export default {
  name: "Balance",
  data() {
    return {
      passenger_account: 0,
      dialog: {
        show: false
      },
      tokenform: {
        username: localStorage.eleToken
      },
      id: {
        passengerId: 0
      },
      tableData: [],
      rechargedata: {
        passengerId: 0,
        rechargedata: 0
      },
      form_rules: {
        rechargedata: [
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
    this.getlist();
  },
  methods: {
    getlist() {
      var that = this;
      new Promise((resolve, reject) => {
        resolve(
          this.$axios
            .post("http://localhost:8087/passengerinfo/current", that.tokenform)
            .then(res => {
              that.passenger_account = res.data.passengerAccount;
              this.id.passengerId = res.data.passengerId;
              return new Promise((resolve, reject) => {
                resolve(
                  this.$axios
                    .post("http://localhost:8087/wallentinfo/getlist", that.id)
                    .then(res => {
                      this.tableData = res.data;
                      this.tableData.sort((a,b) => b.rechargeTime - a.rechargeTime);
                    })
                );
              });
            })
        );
      });
    },
    onSubmit(rechargedata) {
      this.$refs[rechargedata].validate(valid => {
        if (valid) {
          //表单数据验证完成之后，提交数据;
          var that = this;
          new Promise((resolve, reject) => {
            resolve(
              this.$axios
                .post(
                  "http://localhost:8087/passengerinfo/current",
                  that.tokenform
                )
                .then(res => {
                  that.rechargedata.passengerId = res.data.passengerId;
                  return new Promise((resolve, reject) => {
                    resolve(
                      this.$axios
                        .post(
                          "http://localhost:8087/passengerinfo/pcharge",
                          this.rechargedata
                        )
                        .then(res => {
                          if (res.data == "充值成功") {
                            this.dialog.show = false;
                            this.$message({
                              message: res.data,
                              type: "success"
                            });
                            this.getlist();
                          } else {
                            this.$message({
                              message: "充值失败",
                              type: "success"
                            });
                          }
                        })
                    );
                  });
                })
            );
          });
        }
      });
    },
    handleCharge() {
      this.dialog.show = true;
    },
    handleDelete(index, row) {
      this.$axios.post("http://localhost:8087/wallentinfo/delete",row).then(res => {
        this.$message({
          message: "删除成功",
          type: "success"
        });
        this.getlist();
      });
    }
  }
};
</script>

<style scoped>
.balancewapper {
  padding: 0;
  margin: 0;
  width: 100%;
  height: auto;
}

.balance {
  padding: 0;
  margin: 0;
  width: 100%;
  height: 150px;
  background-color: orange;
  border-radius: 8px;
}

.dashed {
  padding: 0;
  height: 12px;
  border-bottom: 1px gray dashed;
}

.show {
  padding: 0;
  margin-top: 12px;
  height: 80px;
}

.show h1 {
  padding: 0;
  margin: 0 0 0 12px;
  font-size: 45px;
  font-family: "microsoft yahei";
}

.showdesc {
  padding: 0;
  margin-left: 12px;
  font-size: 15px;
  font-family: "microsoft yahei";
}

.btn {
  padding: 0;
  margin-left: 12px;
  margin-bottom: 30px;
}
</style>
