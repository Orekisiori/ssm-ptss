<template>
	<div>
		<div>
      <el-row>
        <el-col :span="24">
          <div class="grid-content">
            <el-input
              placeholder="请输入内容"
              align="center"
              v-model="searchOrders.searchtext"
              style="width:200px"
              clearable
            ></el-input>
            <el-button icon="el-icon-search" @click="searchOrder" circle></el-button>
          </div>
        </el-col>
      </el-row>
    </div>

		<!-- 订单列表 -->
		<div>
			<template>
        <el-table :data="AllOrderList" style="width: 100%">
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

		<!-- 查询列表 -->
		<div>
			<el-dialog title="提示" :visible.sync="centerDialogVisible" width="60%" center>
        <template>
          <el-table :data="searchresultList" style="width: 100%">
            <el-table-column align="center" prop="orderId" label="订单ID" width="180"></el-table-column>
            <el-table-column align="center" prop="passengerName" label="购买乘客" width="180"></el-table-column>
            <el-table-column align="center" prop="passengerIdentity" label="乘客身份证" width="200"></el-table-column>
            <el-table-column align="center" prop="orderPrice" label="订单金额"></el-table-column>
						<el-table-column align="center" prop="orderStatus" label="订单状态"></el-table-column>
          </el-table>
        </template>
      </el-dialog>
		</div>
	</div>
</template>

<script>
	export default {
		name: "AllOrders",
		data() {
			return {
				centerDialogVisible: false,
				AllOrderList: [],
				searchOrders: {
					searchtext: ""
				},
				searchresultList: []
			}
		},
		created() {
			this.getList();
		},
		methods: {
			getList() {
				this.$axios.get("http://localhost:8087/orders/getAllOrders")
				  .then(res => {
						this.AllOrderList = res.data;
						this.AllOrderList.sort((a,b) => a.purchaseDate - b.purchaseDate);
					})
			},
			searchOrder() {
				this.$axios.post("http://localhost:8087/orders/selectOrder",this.searchOrders)
				  .then(res => {
						this.searchresultList = res.data;
						this.centerDialogVisible = true;
					})
			},
		}
	}
</script>

<style scoped>
  .demo-table-expand {
    font-size: 0;
  }
  .demo-table-expand label {
    width: 90px;
    color: #99a9bf;
  }
  .demo-table-expand .el-form-item {
    margin-right: 0;
    margin-bottom: 0;
    width: 50%;
  }
</style>
