import Vue from 'vue'
import VueRouter from 'vue-router'

//404
const NotFound = () => import('../views/404.vue')

const Login = () => import('../components/common/Login.vue')
const Home = () => import('../views/Home.vue')
const Proflie = () => import('../views/Profile.vue')
const Registration = () => import('../views/Registration.vue')
const Admin = () => import('../views/Admin.vue')
const Company = () => import('../views/Company.vue')

const Profiles = () => import('../views/Proflieitem/Profiles.vue')
const Balance = () => import('../views/Proflieitem/Balance.vue')
const Orders = () => import('../views/Proflieitem/Orders.vue')

const PassengerReg = () => import('../views/registrations/passenger_reg.vue')
const CompanyReg = () => import('../views/registrations/company_reg.vue')
const AdminReg = () => import('../views/registrations/admin_reg.vue')

// 后台管理员界面--用户管理
const AdminHome = () => import('../views/admins/usermanage/AdminHome.vue')
const PassengerManage = () => import('../views/admins/usermanage/PassengerManage.vue')
const CompanyManage = () => import('../views/admins/usermanage/CompanyManage.vue')
const AdminManage = () => import('../views/admins/usermanage/AdminManage.vue')
// 后台管理员界面--订单管理
const AllOrders = () => import('../views/admins/ordersmanage/AllOrders.vue')
const CompanyOrders = () => import('../views/admins/ordersmanage/CompanyOrders.vue')
// 后台管理员界面--资金管理
const CurrentFounds = () => import('../views/admins/accountmanage/CurrentFounds.vue')
const WithdrawApplication = () => import('../views/admins/accountmanage/WithdrawApplication.vue')
// 后台管理员界面--平台设置
const Announcement = () => import('../views/admins/systemsetting/Announcement.vue')
// const Dividend = () => import('../views/admins/systemsetting/Dividend.vue')
const PageSetting = () => import('../views/admins/systemsetting/PageSetting.vue')
// 后台管理员界面--管理员登录日志
const LoginLog = () => import('../views/admins/loginlog/LoginLog.vue')


//航空公司管理
//主界面
const Ahome = () => import("../views/airlines/ahome.vue")
//用户管理
const Ausermanage = () => import("../views/airlines/ausermanage.vue")
//添加机票
const AddTickets = () => import("../views/airlines/addticket.vue") 
//航班管理
const Aticketmanage = () => import("../views/airlines/aticketmanage.vue")
//资金管理

//资金详情
const AssentDtail = () => import("../views/airlines/assentdetail.vue")

Vue.use(VueRouter)

const routes = [
	{
		path: '*',
		name: '/404',
		component: NotFound
	},
	{
		path: '',
		redirect: '/login'
	},
	{
		path: '/login',
		component: Login
	},
	{
		path: '/company',
		component: Company,
		children: [
			{
				path: '',
				redirect: 'ahome'
			},
			{
				path: 'ahome',
				component: Ahome
			},
			{
				path: 'addticket',
				component: AddTickets
			},
			{
				path: 'ausermanage',
				component: Ausermanage
			},
			{
				path: 'aticketmanage',
				component: Aticketmanage
			},
			{
				path: 'assentdetail',
				component: AssentDtail
			}
		]
	},
	{
		path: '/registration',
		component: Registration,
	},
	{
		path: '/passenger_register',
		component: PassengerReg
	},
	{
		path: '/company_register',
		component: CompanyReg
	},
	{
		path: '/admin_register',
		component: AdminReg
	},
	{
		path: '/home',
		component: Home
	},
	{
		path: '/proflie',
		component: Proflie,
		children: [
			{
				path: '',
				redirect: 'proflie'
			},
			{
				path: 'profiles',
				component: Profiles
			},
			{
				path: 'balance',
				component: Balance
			},
			{
				path: 'orders',
				component: Orders
			}
		]
	},
	// 后台管理员路由
	{
		path: '/admin',
		component: Admin,
		children: [
			{
				path: '',
				redirect: 'admin'
			},
			{
				path: 'adminhome',
				component: AdminHome
			},
			{
				path: 'passengermanage',
				component: PassengerManage
			},
			{
				path: 'companymanage',
				component: CompanyManage
			},
			{
				path: 'adminmanage',
				component: AdminManage
			},
			{
				path: 'allorders',
				component: AllOrders
			},
			{
				path: 'companyorders',
				component: CompanyOrders
			},
			{
				path: 'currentfounds',
				component: CurrentFounds
			},
			{
				path: 'withdrawapplication',
				component: WithdrawApplication
			},
			{
				path: 'announcement',
				component: Announcement
			},
			{
				path: 'pagesetting',
				component: PageSetting
			},
			{
				path: 'loginlog',
				component: LoginLog
			},
		]
	},
	
]

const router = new VueRouter({
	routes,
	mode: 'history'
})

//路由守卫
router.beforeEach((to,from,next) => {
	const isLogin = localStorage.eleToken ? true : false;
	if(to.path == '/login' || to.path == '/registration' || to.path == '/passenger_register' || to.path == '/company_register' || to.path == '/admin_register') {
		next();
	}
	else {
		isLogin ? next() : next('/login')
	}
})


export default router
