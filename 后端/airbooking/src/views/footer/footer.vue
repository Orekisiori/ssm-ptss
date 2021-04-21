<template>
  <div class="footer">
    <div class="whychose">
      <div class="h1 h1_t">
        <i class="el-icon-circle-check"></i>
        为什么选择本订票系统？
      </div>
    </div>
    <div class="shows">
			<dl>
				<img src="../../assets/img/f1.png" alt="">
				<i>品牌优势</i>
			</dl>
			<dl>
				<img src="../../assets/img/f2.png" alt="">
				<i>全国代理</i>
			</dl>
			<dl>
				<img src="../../assets/img/f3.png" alt="">
				<i>路线丰富</i>
			</dl>
			<dl>
				<img src="../../assets/img/f4.png" alt="">
				<i>成团率高</i>
			</dl>
			<dl>
				<img src="../../assets/img/f5.png" alt="">
				<i>高性价比</i>
			</dl>
			<dl>
				<img src="../../assets/img/f6.png" alt="">
				<i>会员专享</i>
			</dl>
			<dl>
				<img src="../../assets/img/f7.png" alt="">
				<i>出游保障</i>
			</dl>
    </div>
		<!-- 入驻公司 -->
		<div class="recompanies">
			<i v-for="(item,index) in companies" :key="index">
				<span>{{item.airlineName}}</span>
			</i>
		</div>
		<br>
		<div class="version">
			<h2>©️陈俊宏</h2>
		</div>
  </div>
</template>

<script>
export default {
	name: "Footer",
	data() {
		return {
			companies: []
		}
	},
	created() {
		this.gitClist();
	},
	methods: {
		gitClist() {
      this.$axios.get("http://localhost:8087/airlineInfo/getList").then(res => {
				this.companies = res.data;
      });
    },
	}
};
</script>

<style scoped>
.footer {
  padding: 0;
  margin: 0;
  width: 100%;
  height: auto;
  background-color: white;
  text-align: center;
}
.whychose {
  padding: 0;
  margin: 0;
  width: 100%;
  height: auto;
}

.h1_t {
  float: left;
  font-size: 18px;
  font-weight: normal;
  color: #5c5c5c;
  height: 53px;
  line-height: 53px;
}

/* .whychose dl {
    font-size: 13px;
    float: left;
    width: 14.2%;
    text-align: center;
    color: #929292;
	} */

.shows {
  padding: 0;
  margin: 0;
  width: 100%;
  height: 160px;
  float: left;
}

.shows dl {
	padding: 0;
	margin: 0;
	float: left;
	width: 14.23%;
	height: 100%;
}

.shows dl img {
	padding: 0;
	margin: 0;
	float: left;
	width: 100%;
	height: 87%;
}

.shows dl i {
	padding: 0;
	margin: 0;
	text-align: center;
	color:silver;
}

.recompanies {
	padding: 0;
	margin-top: 10px;
	width: 100%;
	height: auto;
	float: left;
	background-color: tan;
}

.recompanies span {
	float: left;
  padding: 0;
	margin: 0;
	width: 15%;
	height: 100%;
	font-size: 20px;
	background-color: tomato;
	color: darkgray;
}

.version {
	padding: 0;
	width: 100%;
	height: auto;
	float: left;
	text-align: center;
}
</style>
