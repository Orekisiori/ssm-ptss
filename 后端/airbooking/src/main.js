import Vue from 'vue'
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
import App from './App.vue'
import store from './store'
import Element from 'element-ui';
import router from './router'
import axios from './http.js'
//表格自适应
import AFTableColumn from 'af-table-column'
//时间过滤器
import moment from 'moment/moment'
//引入图表
import echarts from 'echarts'
Vue.prototype.$echarts = echarts

Vue.use(AFTableColumn)
Vue.use(ElementUI);
Vue.config.productionTip = false
Vue.use(Element, { size: 'small', zIndex: 3000 });
Vue.prototype.$axios = axios;

Vue.filter('date-format',function(value,formatStr='YYYY-MM-DD HH:mm:ss'){
  return moment(value).format(formatStr)
})

new Vue({
  store,
	router,
  render: h => h(App)
}).$mount('#app')
