
import Vue from 'vue'
import './plugins/axios'
import App from './App'
import router from './router'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import store from './store/index.js'
import moment from 'moment/moment'
import AFTableColumn from 'af-table-column'

Vue.use(AFTableColumn)
Vue.use(ElementUI)

Vue.filter('moment', function (value, formatString) {
  return moment(value).format('YYYY-MM-DD HH:mm:ss')
});

/* eslint-disable no-new */
new Vue({
  store,
  el: '#app',
  render: h=>h(App),
  router,                /*router 代表该对象包含 Vue Router，并使用项目中的路由*/
  components: { App },   /*components 表示该对象包含的 Vue 组件，*/
  template: '<App/>'     /*template 是用一个字符串模板作为 Vue 实例的标识使用，类似于定义一个 html 标签*/
})
