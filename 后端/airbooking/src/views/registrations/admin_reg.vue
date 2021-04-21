<template>
  <div class="wapper">
    <el-container>
      <el-header></el-header>
      <el-main>
        <el-form
          ref="adminInfo"
          :model="adminInfo"
          :rules="rules"
          label-width="90px"
          style="width: 400px;"
        >
          <el-form-item label="名字" prop="adminName">
            <el-input v-model="adminInfo.adminName"></el-input>
          </el-form-item>
          <el-form-item label="密码" prop="adminPassword">
            <el-input type="password" v-model="adminInfo.adminPassword"></el-input>
          </el-form-item>
          <el-form-item label="确认密码" prop="adminPassword2">
            <el-input type="password" v-model="adminInfo.adminPassword2"></el-input>
          </el-form-item>
          <el-form-item label="手机号" prop="adminPhone">
            <el-input v-model="adminInfo.adminPhone" maxlength="11"></el-input>
          </el-form-item>
          <el-form-item style="margin-left: 46px;">
            <el-button type="success" @click="submitFrom('adminInfo')" round>注册</el-button>
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
  name: "AdminReg",
  data() {
    var validatePass = (rule, value, callback) => {
      if (value !== this.adminInfo.adminPassword) {
        callback(new Error("两次输入密码不一致!"));
      } else {
        callback();
      }
    };
    return {
      adminInfo: {
        adminName: "",
        adminPassword: "",
        adminPassword2: "",
        adminPhone: ""
      },
      rules: {
        adminName: [
          {
            required: true,
            message: "名字不能为空",
            trigger: "blur"
          },
          {
            min: 2,
            max: 30,
            message: "长度在2-12之间",
            trigger: "blur"
          }
        ],
        adminPassword: [
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
        adminPassword2: [
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
          },
          {
            validator: validatePass,
            trigger: "blur"
          }
        ],
        adminPhone: [
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
              "http://localhost:8087/register/adminregister",
              that.adminInfo
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
              } else if (res.data == "用户名已存在") {
                //用户名已存在
                this.$message({
                  message: "用户名已存在",
                  type: "warning"
                });
                this.$refs[formName].resetFields();
              } else {
                //用户名已存在
                this.$message({
                  message: "注册失败",
                  type: "error"
                });
              }
            });
        } else {
          console.log("error submit!!");
          return false;
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
