<template>
  <div>
    <div>
      <template>
        <el-select v-model="value" placeholder="请选择">
          <el-option
            v-for="item in AllCompanyName"
            :key="item.airlineName"
            :label="item.airlineName"
            :value="item.airlineName"
          ></el-option>
        </el-select>
				<el-button icon="el-icon-search" @click="searchListBycompanyname" circle></el-button>
      </template>
    </div>

				<!-- 订单列表 -->
		<div>
			<template>
        <el-table :data="searchresultList" style="width: 100%">
          <el-table-column type="expand">
            <template slot-scope="props">
              <el-form label-position="left" inline class="demo-table-expand">
                <el-form-item label="订单ID">
                  <span>{{ props.row.orderId }}</span>
                </el-form-item>
                <el-form-item label="购买乘客">
                  <span>{{ props.row.passengerName }}</span>
                </el-form-item>
                <el-form-item label="乘客身份证">
                  <span>{{ props.row.passengerIdentity }}</span>
                </el-form-item>
                <el-form-item label="乘客手机号">
                  <span>{{ props.row.passengerPhone }}</span>
                </el-form-item>
                <el-form-item label="出发地">
                  <span>{{ props.row.startplace }}</span>
                </el-form-item>
                <el-form-item label="目的地">
                  <span>{{ props.row.endplace}}</span>
                </el-form-item>
								<el-form-item label="登机时间">
                  <span>{{ props.row.starttime | date-format}}</span>
                </el-form-item>
								<el-form-item label="预计到达时间">
                  <span>{{ props.row.endtime | date-format}}</span>
                </el-form-item>
								<el-form-item label="机票单价">
                  <span>{{ props.row.ticketPrice}}</span>
                </el-form-item>
								<el-form-item label="购买数量">
                  <span>{{ props.row.purchaseNumber}}</span>
                </el-form-item>
								<el-form-item label="订单金额">
                  <span>{{ props.row.orderPrice}}</span>
                </el-form-item>
								<el-form-item label="订单状态">
                  <span>{{ props.row.orderStatus}}</span>
                </el-form-item>
              </el-form>
            </template>
          </el-table-column>
          <el-table-column label="购买乘客" prop="passengerName"></el-table-column>
          <el-table-column label="乘客身份证" prop="passengerIdentity"></el-table-column>
          <el-table-column label="乘客手机号" prop="passengerPhone"></el-table-column>
					<el-table-column label="订单金额" prop="orderPrice"></el-table-column>
					<el-table-column label="订单状态" prop="orderStatus"></el-table-column>
        </el-table>
      </template>
		</div>
  </div>
</template>

<script>
export default {
	name: "CompanyOrders",
	data() {
		return {
			AllCompanyName: [],
			value: '',
			searchform: {
				value:''
			},
			//保存查询列表
			searchresultList:[]
		}
	},
	created() {
		this.getCompanyName();
	},
	methods: {
		getCompanyName() {
			this.$axios.get("http://localhost:8087/airlineInfo/getAllairlineName")
			  .then(res => {
					this.AllCompanyName  = res.data;
				})
		},
		searchListBycompanyname() {
			this.searchform.value = this.value;
			this.$axios.post("http://localhost:8087/orders/searchListBycompanyname",this.searchform)
			  .then(res => {
					this.searchresultList = res.data;
				})
		}
	}
};
</script>

<style>
</style>
