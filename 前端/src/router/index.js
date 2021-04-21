import Vue from 'vue'
import VueRouter from 'vue-router'
import App from "../App";

//404界面
const NotFound = () => import('../views/404.vue')
//登陆界面
const Login = () => import('../views/Login.vue')
//用户界面
const Home = () => import('../views/passenger/home/Home.vue')
//用户个人中心
const Profile = () => import('../views/passenger/profile/Profile.vue')
const Profiles = () => import('../views/passenger/profile/Profiles.vue')
const Balance = () => import('../views/passenger/profile/Balance.vue')
const Orders = () => import('../views/passenger/profile/Orders.vue')
//注册界面
const Registration = () => import('../views/Registration')
const UserReg = () => import('../views/passenger/UserReg')
const CompanyReg = () => import('../views/company/CompanyReg')
//航空公司路径
const Company = () => import('../views/company/Home')
//航空公司界面
const CompanyHome = () => import("../views/company/CompanyHome.vue")
const Ausermanage = () => import("../views/company/CompanyUserManage.vue")
const AddTickets = () => import("../views/company/AddTicket.vue")
const Aticketmanage = () => import("../views/company/CompanyTicketManage.vue")
const AssentDtail = () => import("../views/company/AssentDetails.vue")
//后台管理员界面
const Administrator = () => import('../views/administrator/Home')
//资金管理
const CurrentFounds = () => import('../views/administrator/accountmanage/CurrentFounds')
const WithdrawApplication = () => import('../views/administrator/accountmanage/WithdrawApplication')
//订单管理
const AllOrders = () => import('../views/administrator/ordersmanage/AllOrders.vue')
const CompanyOrders = () => import('../views/administrator/ordersmanage/CompanyOrders.vue')
//平台设置
const Announcement = () => import('../views/administrator/systemsetting/Announcement.vue')
const PageSetting = () => import('../views/administrator/systemsetting/PageSetting.vue')
//用户管理
const AdminHome = () => import('../views/administrator/passengerManage/AdminHome.vue')
const PassengerManage = () => import('../views/administrator/passengerManage/PassengerManage.vue')
const CompanyManage = () => import('../views/administrator/passengerManage/CompanyManage.vue')
const AdminManage = () => import('../views/administrator/passengerManage/AdminManage.vue')
//登录日志
const LoginLog = () => import('../views/administrator/log/LoginLog.vue')

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
    path: '/home',
    component: Home
  },
  {//用户个人中心路由
    path: '/profile',
    component: Profile,
    children: [
      {
        path: '',
        redirect: 'profile'
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
  {
    path: '/registration',
    component: Registration,
  },
  {
    path: '/user_register',
    component: UserReg
  },
  {
    path: '/company_register',
    component: CompanyReg
  },
  {
    path: '/company',
    component: Company,
    children: [
      {
        path: 'home',
        component: CompanyHome
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
  {// 后台管理员路由
    path: '/admin',
    component: Administrator,
    children: [
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
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
