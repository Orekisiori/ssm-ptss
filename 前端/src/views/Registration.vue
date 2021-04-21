<template>
  <div>
    <el-container>
      <el-main>
        <div class="register">
          <div class="register_passenger">
            <div class="imagelist" style="background-color: #AED6F1;" @click="register_passenger">
              <span>新用户注册</span>
            </div>
          </div>
          <div class="register_company">
            <div class="imagelist" style="background-color: #AED6F1;" @click="register_company">
              <span>航空公司登记</span>
            </div>
          </div>
<!--          <div class="register_admin">
            <div class="imagelist" style="background-color: #AED6F1;" @click="register_admin">
              <span>管理员</span>
            </div>
          </div>-->
        </div>
      </el-main>
    </el-container>
  </div>
</template>

<script>
  export default {
    name: "Registration",
    data() {
      return {

      }
    },
    methods: {
      register_passenger() {
        this.$router.push('/user_register')
      },
      register_company() {
        this.$router.push('/company_register')
      },
      register_admin() {
        this.$router.push('/admin_register')
      }
    }
  }
</script>

<style scoped>
  .register {
    width: 100%;
    height: 510px;
    margin-top: 200px;
  }

  .register_passenger {
    float: left;
    width: 50%;
    height: 510px;
    border-radius: 15px;
    overflow: hidden;
  }

  .register_company {
    float: left;
    width: 50%;
    height: 510px;
    border-radius: 15px;
    overflow: hidden;
  }

  .register_admin {
    float: left;
    width: 33.3%;
    height: 510px;
    border-radius: 15px;
    overflow: hidden;
  }

  .imagelist {
    width: 80%;
    height: 270px;
    margin: 23px 23px 23px 23px;
    transition: all 0.6s;
    border-radius: 15px;
    text-align: center;
  }

  .imagelist:hover {
    transform: scale(1.4);
  }

  .imagelist span {
    line-height: 270px;
    font-size: 28px;
    font-family: "microsoft yahei";
    color: #F4F6F7;
  }
</style>
