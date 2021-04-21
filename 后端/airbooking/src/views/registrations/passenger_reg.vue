<template>
  <div class="wapper">
    <el-container>
      <el-header></el-header>
      <el-main>
        <el-form
          ref="PassengerInfo"
          :model="PassengerInfo"
          :rules="rules"
          label-width="80px"
          style="width: 400px;"
        >
          <el-form-item label="用户名" prop="passengerName">
            <el-input v-model="PassengerInfo.passengerName"></el-input>
          </el-form-item>
          <el-form-item label="密码" prop="passengerPassword">
            <el-input type="password" v-model="PassengerInfo.passengerPassword"></el-input>
          </el-form-item>
          <el-form-item label="确认密码" prop="passengerPassword2">
            <el-input type="password" v-model="PassengerInfo.passengerPassword2"></el-input>
          </el-form-item>
          <el-form-item label="性别" prop="passengerGender">
            <el-radio-group v-model="PassengerInfo.passengerGender">
              <el-radio label="男"></el-radio>
              <el-radio label="女"></el-radio>
            </el-radio-group>
          </el-form-item>
          <el-form-item label="身份证号" prop="passengerIdentity">
            <el-input v-model="PassengerInfo.passengerIdentity" minlength="18" maxlength="18"></el-input>
          </el-form-item>
          <el-form-item label="手机号" prop="passengerPhone">
            <el-input v-model="PassengerInfo.passengerPhone" maxlength="11"></el-input>
          </el-form-item>
          <el-form-item style="margin-left: 46px;">
            <el-button type="success" @click="submitFrom('PassengerInfo')" round>注册</el-button>
            <el-button type="danger" @click="reback" round>返回</el-button>
          </el-form-item>
        </el-form>
      </el-main>
      <el-footer></el-footer>
    </el-container>
  </div>
</template>

<script>
export default {
  name: "PassengerReg",
  data() {
    var validatePass = (rule, value, callback) => {
      if (value !== this.PassengerInfo.passengerPassword) {
        callback(new Error("两次输入密码不一致!"));
      } else {
        callback();
      }
    };
    return {
      PassengerInfo: {
        passengerName: "",
        passengerPassword: "",
        passengerPassword2: "",
        passengerGender: "男",
        passengerIdentity: "",
        passengerPhone: ""
      },
      rules: {
        passengerName: [
          {
            required: true,
            message: "用户名不能为空",
            trigger: "blur"
          },
          {
            min: 2,
            max: 12,
            message: "长度在2-12之间",
            trigger: "blur"
          }
        ],
        passengerPassword: [
          {
            required: true,
            message: "密码不能为空",
            trigger: "blur"
          },
          {
            min: 6,
            max: 20,
            message: "长度在6-20之间",
            trigger: "blur"
          }
        ],
        passengerPassword2: [
          {
            required: true,
            message: "确认密码不能为空",
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
        ],
        passengerIdentity: [
          {
            required: true,
            message: "身份证号不能为空",
            trigger: "blur"
          },
          {
            pattern: /^[1-9]\d{7}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}$|^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}([0-9]|X)$/,
            message: "请输入正确的身份证号",
            trigger: "blur"
          }
        ],
        passengerPhone: [
          {
            required: true,
            message: "手机号不能为空",
            trigger: "blur"
          },
          {
            pattern: /^[1](([3][0-9])|([4][5-9])|([5][0-3,5-9])|([6][5,6])|([7][0-8])|([8][0-9])|([9][1,8,9]))[0-9]{8}$/,
            message: "请输入正确的手机号",
            trigger: "blur"
          }
        ]
      }
    };
  },
  methods: {
    submitFrom(formName) {
      this.$refs[formName].validate(valid => {
        if (valid) {
          var that = this;
          this.$axios
            .post(
              "http://localhost:8087/register/pregister",
              that.PassengerInfo
            )
            .then(res => {
              if (res.data == "注册成功") {
                //注册成功
                this.$message({
                  message: "注册成功",
                  type: "success"
                });
                //注册成功之后跳转到登录界面
                this.$router.push("/login");
							}
							else if(res.data == "用户名已存在"){
								//用户名已存在
                this.$message({
                  message: "用户名已存在",
                  type: "warning"
								});
								this.$refs[formName].resetFields();
							}
							else{
								//用户名已存在
                this.$message({
                  message: "注册失败",
                  type: "error"
                });
							}
            });
        }
      });
    },
  reback() {
    this.$router.push("/login");
  }
  }
};
</script>

<style scoped>
.wapper {
  width: 450px;
  height: auto5;
  margin: 0 35% 30% 35%;
}

.avatar-uploader .el-upload {
  border: 1px dashed #5dade2;
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
}

.avatar-uploader .el-upload:hover {
  border-color: #409eff;
}

.avatar-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 178px;
  height: 178px;
  line-height: 178px;
  text-align: center;
}

.avatar {
  width: 178px;
  height: 178px;
  display: block;
}
</style>
