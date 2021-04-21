import axios from 'axios';
import { Message,Loading } from 'element-ui';
import qs from 'qs';

//加载动画
let loading;
function startLoading() {
	loading = Loading.service({
		lock: true,
		text: '拼命加载中',
		background:'rgba(0,0,0,0.7)'
	});
}
//结束加载动画
function endLoading() {
	loading.close();
}

//请求拦截
axios.interceptors.request.use(config => {
	config.data = qs.stringify(config.data);
	//加载动画
	startLoading();
	return config;
},error => {
	return Promise.reject(error);
})

//响应拦截
axios.interceptors.response.use(response => {
	//结束加载动画
	endLoading();
	return response;
},error => {
	//结束加载动画
	endLoading();
	//错误消息提醒
	Message.error(error.response.data);
	return Promise.reject(error);
})


export default axios;