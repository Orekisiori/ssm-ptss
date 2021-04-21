<template>
  <div class="login">
    <div class="wapper">
      <h2>欢迎登录</h2>
      <el-form
        :model="loginform"
        status-icon
        :rules="rules"
        ref="loginform"
        style="width: 280px;margin-left: 110px;"
      >
        <el-form-item prop="username">
          <el-input
            prefix-icon="el-icon-user"
            placeholder="用户名"
            v-model="loginform.username"
            clearable
          ></el-input>
        </el-form-item>
        <el-form-item prop="password">
          <el-input
            prefix-icon="el-icon-key"
            placeholder="密码"
            type="password"
            v-model="loginform.password"
            clearable
          ></el-input>
        </el-form-item>
        <el-form-item>
          <el-radio v-model="radio" label="1">乘客</el-radio>
          <el-radio v-model="radio" label="2">公司</el-radio>
          <el-radio v-model="radio" label="3">管理员</el-radio>
        </el-form-item>
        <el-form-item>
          <el-button type="success" @click="loginbtn" style="margin-right: 45px" round>登录</el-button>
          <el-button @click="resetForm()" type="info" round>重置</el-button>
        </el-form-item>
        <el-form-item>
          <h3>
            没有账户？
            <el-link type="primary" @click="register" style="font-size: 17px;">去注册</el-link>
          </h3>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script>
export default {
  name: "Login",
  data() {
    return {
      radio: "1",
      loginform: {
        username: "",
        password: ""
      },
      rules: {
        username: [
          {
            required: true,
            message: "用户名不能为空",
            trigger: "blur"
          },
          {
            min: 2,
            max: 30,
            message: "长度在2-30之间",
            trigger: "blur"
          }
        ],
        password: [
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
        ]
      }
    };
  },
  methods: {
    loginbtn() {
      if (this.radio == "1") {
        var that = this;
        this.$axios
          .post("http://localhost:8087/register/plogin", that.loginform)
          .then(res => {
            if (res.data == "登录成功") {
              //登录成功之后，将token保存到localStorage
              const token = this.loginform.username;
              localStorage.setItem("eleToken", token);
              //将token保存至vuex中
              this.$store.dispatch("setAutenticated", !this.isEmpty(token));
              this.$store.dispatch("setUser", token);

              this.$message({
                message: res.data,
                type: "success"
              });
              this.$router.push("/home");
            } else if (res.data == "密码错误") {
              this.$message({
                message: res.data,
                type: "warning"
              });
            } else if (res.data == "用户名不存在") {
              this.$message({
                message: res.data,
                type: "warning"
              });
            } else {
              this.$message({
                message: "登录失败",
                type: "error"
              });
            }
          });
      } else if (this.radio == "2") {
        var that = this;
        this.$axios
          .post("http://localhost:8087/register/airlinrlogin", that.loginform)
          .then(res => {
            if (res.data == "登录成功") {
              //登录成功之后，将token保存到localStorage
              const token = this.loginform.username;
              localStorage.setItem("eleToken", token);
              //将token保存至vuex中
              this.$store.dispatch("setAutenticated", !this.isEmpty(token));
              this.$store.dispatch("setUser", token);

              this.$message({
                message: res.data,
                type: "success"
              });
              this.$router.push("/company");
            } else if (res.data == "密码错误") {
              this.$message({
                message: res.data,
                type: "warning"
              });
            } else if (res.data == "用户名不存在") {
              this.$message({
                message: res.data,
                type: "warning"
              });
            } else if (res.data == "未审核") {
              this.$message({
                message: res.data,
                type: "error"
              });
            } else {
              this.$message({
                message: "登录失败",
                type: "error"
              });
            }
          });
      } else if (this.radio == "3") {
        var that = this;
        this.$axios
          .post("http://localhost:8087/register/adminlogin", that.loginform)
          .then(res => {
            if (res.data == "登录成功") {
              //登录成功之后，将token保存到localStorage
              const token = this.loginform.username;
              localStorage.setItem("eleToken", token);
              //将token保存至vuex中
              this.$store.dispatch("setAutenticated", !this.isEmpty(token));
              this.$store.dispatch("setUser", token);

              this.$message({
                message: res.data,
                type: "success"
              });
              this.$router.push("/admin/adminhome");
            } else if (res.data == "密码错误") {
              this.$message({
                message: res.data,
                type: "warning"
              });
            } else if (res.data == "用户名不存在") {
              this.$message({
                message: res.data,
                type: "warning"
              });
            } else {
              this.$message({
                message: "登录失败",
                type: "error"
              });
            }
          });
      }
    },
    isEmpty(value) {
      return (
        value === undefined ||
        value === null ||
        (typeof value === "object" && Object.keys(value).length === 0) ||
        (typeof value === "string" && value.trim().length === 0)
      );
    },
    register() {
      this.$router.push("/registration");
    },
    resetForm() {
      this.loginform.username = "";
      this.loginform.password = "";
    }
  }
};
</script>

<style scoped>
@import url("../../assets/base.css");

.login {
  width: 100%;
  height: 600px;
  position: relative;
}

.wapper {
  position: absolute;
  top: 50%;
  left: 50%;
  margin-top: -100px;
  margin-left: -250px;
  width: 500px;
  height: 370px;
  border-radius: 5px;
  border: #409eff;
  border-style: solid;
}

h3 {
  margin-top: 0px;
  padding: 0;
}

.wapper h2 {
  text-align: center;
}

.wapper form {
  text-align: center;
}

.wapper button {
  text-align: center;
}

.loginbtn {
  margin: 13px 0 0 0;
  text-align: center;
}

.register {
  margin: 13px 0 0 0;
  text-align: center;
}

.register a {
  cursor: pointer;
}
</style>
