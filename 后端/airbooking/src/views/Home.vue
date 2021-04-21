<template>
	<el-container>
		<el-header>
			<Header></Header>
			<div class="middle">
				<div>
					<RightBer></RightBer>
				</div>
				<div>
					<LeftBer></LeftBer>
				</div>
			</div>
			<Show></Show>
			<Footer></Footer>
		</el-header>
	</el-container>
</template>

<script>
	import Header from './header/header.vue'
	import RightBer from './right/rightber.vue'
	import LeftBer from './left/leftber.vue'
	import Show from './show/show.vue'
	import Footer from './footer/footer.vue'
	export default {
		name: 'Home',
		components: {
			Header,
			RightBer,
			LeftBer,
			Show,
			Footer
		}
	}
</script>

<style scoped>
	@import url("../assets/base.css");

	.middle {
		margin-top: 50px;
		padding: 0;
		margin: 0 0 0 0;
		width: 100%;
		height: 400px;
	}
</style>
