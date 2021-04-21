<template>
	<el-container>
		<el-header style="background-color: #e4f2f2;">
			<el-row>
				<el-col :span="12">
					<div class="grid-content">
						<el-link type="info" style='font-size: 20px; font-family: "microsoft yahei";margin-top: 15px;'>后台管理系统</el-link>
					</div>
				</el-col>
				<el-col :span="12">
					<div class="grid-content">
						<div class="header-right">
							<div class="header-user-con">
								<div class="btn-bell">
									<el-button type="success" @click="logout" icon="el-icon-check" circle></el-button>
								</div>
								<div class="user-avator">
									<el-avatar icon="el-icon-user-solid"></el-avatar>
								</div>
								<div class="user-name">
									<span>{{tokenform.username}}</span>
								</div>
							</div>
						</div>
					</div>
				</el-col>
			</el-row>
		</el-header>
		<el-container>
			<el-aside width="200px" style="background-color: #e4f2f2;">
				<el-menu router :default-active="$route.path" class="el-menu-vertical-demo" background-color="#e4f2f2"
				 :unique-opened="true">
					<el-menu-item index="/admin/adminhome">
						<i class="el-icon-s-home"></i>
						<router-link to="/admin/adminhome" tag="span">系统首页</router-link>
					</el-menu-item>
					<el-submenu index="1">
						<template slot="title"><i class="el-icon-menu"></i>用户管理</template>
						<el-menu-item index="/administrator/passengermanage">
							<router-link to="/administrator/passengermanage" tag="span">乘客管理</router-link>
						</el-menu-item>
						<el-menu-item index="/administrator/companymanage">
							<router-link to="/administrator/companymanage" tag="span">公司管理</router-link>
						</el-menu-item>
						<el-menu-item index="/administrator/adminmanage">
							<router-link to="/administrator/adminmanage" tag="span">管理员</router-link>
						</el-menu-item>
					</el-submenu>
					<el-submenu index="2">
						<template slot="title"><i class="el-icon-s-order"></i>订单管理</template>
						<el-menu-item index="/administrator/allorders">
							<router-link to="/administrator/allorders" tag="span">所有订单</router-link>
						</el-menu-item>
						<el-menu-item index="/administrator/companyorders">
							<router-link to="/administrator/companyorders" tag="span">公司订单</router-link>
						</el-menu-item>
					</el-submenu>
					<el-submenu index="3">
						<template slot="title"><i class="el-icon-s-finance"></i>资金管理</template>
						<el-menu-item index="/administrator/currentfounds">
							<router-link to="/administrator/currentfounds" tag="span">现有资金</router-link>
						</el-menu-item>
						<el-menu-item index="/administrator/withdrawapplication">
							<router-link to="/administrator/withdrawapplication" tag="span">提现管理</router-link>
						</el-menu-item>
					</el-submenu>
					<el-submenu index="4">
						<template slot="title"><i class="el-icon-message-solid"></i>平台设置</template>
						<el-menu-item index="/administrator/announcement">
							<router-link to="/administrator/announcement" tag="span">公告管理</router-link>
						</el-menu-item>
					</el-submenu>
					<el-menu-item index="/admin/loginlog">
						<i class="el-icon-info"></i>
						<router-link to="/admin/loginlog" tag="span">登录日志</router-link>
					</el-menu-item>
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
		name: "Admin",
		data() {
			return {
				path: '/admin/adminhome',
				tokenform: {
					username: localStorage.eleToken
				}
			}
		},
		activated() {
			this.$router.push(this.path);
		},
		beforeRouteLeave(to, from, next) {
			this.path = this.$route.path;
			next()
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
	}
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
