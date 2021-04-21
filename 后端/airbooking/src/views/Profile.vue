<template>
	<div>
		<Header>
		</Header>
		<el-container>
			<el-menu router :default-active="$route.path" class="el-menu-vertical-demo" @open="handleOpen" @close="handleClose">
				<el-menu-item index="/proflie/profiles">
					<i class="el-icon-user-solid"></i>
					<router-link to="/proflie/profiles"  tag="span">个人中心</router-link>
				</el-menu-item>
				<el-menu-item index="/proflie/balance">	
					<i class="el-icon-coin"></i>
					<router-link to="/proflie/balance" tag="span">我的余额</router-link>
				</el-menu-item>
				<el-menu-item index="/proflie/orders">
					<i class="el-icon-s-order"></i>
					<router-link to="/proflie/orders" tag="span" >订单中心</router-link>
				</el-menu-item>
			</el-menu>
			<el-main>
				<keep-alive><router-view></router-view></keep-alive>
			</el-main>
		</el-container>
	</div>
</template>

<script>
	import Header from './header/header.vue'
	export default {
		name: "Profile",
		data() {
			return {
				path: '/profile/profiles',
			}
		},
		components: {
			Header
		},
		
		activated() {
			this.$router.push(this.path);
		},
		beforeRouteLeave(to,from,next) {
			this.path = this.$route.path;
			next()
		},
		methods: {
			handleOpen(key, keyPath) {
				console.log(key, keyPath);
			},
			handleClose(key, keyPath) {
				console.log(key, keyPath);
			},
			profileinfo() {
				this.$router.push(this.profileinfo);
			}
		}
	}
</script>

<style>

</style>
