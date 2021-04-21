<template>
  <el-container>
    <el-header style="background-color: #e4f2f2;">
      <el-row>
        <el-col :span="12">
          <div class="grid-content">
            <el-link
              type="info"
              style="font-size: 20px; font-family: 'microsoft yahei';margin-top: 15px;"
            >公司后台管理系统</el-link>
          </div>
        </el-col>
        <el-col :span="12">
          <div class="grid-content">
            <el-button type="danger" @click="logout" size="mini" style="float:right;margin-top:15px" round>退出</el-button>
            <div class="header-right">
              <div class="header-user-con">
                <div class="user-avator">
                  <el-avatar icon="el-icon-user-solid"></el-avatar>
                </div>
                <div class="user-name">
                  <span>{{tokenform.airlineName}}</span>
                </div>
              </div>
            </div>
          </div>
        </el-col>
      </el-row>
    </el-header>
    <el-container>
      <el-aside width="200px" style="background-color: #e4f2f2;">
        <el-menu
          router
          :default-active="$route.path"
          class="el-menu-vertical-demo"
          background-color="#e4f2f2"
          :unique-opened="true"
        >
          <el-menu-item index="/company/ahome">
            <i class="el-icon-s-home"></i>
            <router-link to="/company/ahome" tag="span">系统首页</router-link>
          </el-menu-item>
          <el-submenu index="1">
            <template slot="title">
              <i class="el-icon-s-order"></i>航班管理
            </template>
            <el-menu-item index="/company/addticket">
              <router-link to="/company/addticket" tag="span">添加航班</router-link>
            </el-menu-item>
            <el-menu-item index="/company/aticketmanage">
              <router-link to="/company/aticketmanage" tag="span">航班管理</router-link>
            </el-menu-item>
          </el-submenu>
          <el-submenu index="2">
            <template slot="title">
              <i class="el-icon-s-finance"></i>资金管理
            </template>
            <el-menu-item index="/company/assentdetail">
              <router-link to="/company/assentdetail" tag="span">收益详情</router-link>
            </el-menu-item>
          </el-submenu>
          <el-submenu index="3">
            <template slot="title">
              <i class="el-icon-menu"></i>乘客管理
            </template>
            <el-menu-item index="/company/ausermanage">
              <router-link to="/company/ausermanage" tag="span">乘客管理</router-link>
            </el-menu-item>
          </el-submenu>
        </el-menu>
      </el-aside>
      <el-main>
        <router-view></router-view>
      </el-main>
    </el-container>
  </el-container>
</template>

<script>
export default {
  name: "Company",
  data() {
    return {
      path: "/company/ahome",
      tokenform: {
        airlineName: localStorage.eleToken
      }
    };
  },
  methods: {
    logout() {
      // 清除token
      localStorage.removeItem("eleToken");
      this.$store.dispatch("clearCurrentState");

      // 页面跳转
      this.$router.push("/login");
    }
  }
};
</script>

<style scoped>
.header-right {
  float: right;
}

.header-user-con {
  display: flex;
  height: 60px;
  -webkit-box-align: center;
  align-items: center;
}

.btn-bell {
  position: relative;
  width: 30px;
  text-align: center;
  border: 10px;
  cursor: pointer;
}

.user-avator {
  margin-left: 20px;
}

.user-name {
  margin-left: 20px;
}

/* tag标签 */
.el-row {
  margin-bottom: 20px;
}

.el-col {
  border-radius: 4px;
}

.grid-content {
  border-radius: 4px;
  min-height: 36px;
}

.row-bg {
  padding: 10px 0;
  background-color: #f9fafc;
}
</style>
