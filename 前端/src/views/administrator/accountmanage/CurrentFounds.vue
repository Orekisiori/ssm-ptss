<template>
  <div>
    <div>
      <el-row>
        <el-col :span="24">
          <div class="grid-content">
            <div class="tag-group">
              <el-tag>总计金额:{{sumaccount}}</el-tag>
            </div>
          </div>
        </el-col>
      </el-row>
    </div>

    <div style="margin-top:10px">
      <el-table :data="RechargeList" style="width: 100%;" stripe show-overflow-tooltip="true">
        <af-table-column align="center" label="充值日期" width="190">
          <template slot-scope="scope">
            <i class="el-icon-time"></i>
            <span style="margin-left: 10px">{{ scope.row.rechargeTime | date-format}}</span>
          </template>
        </af-table-column>
        <af-table-column align="center" label="充值人" width="150">
          <template slot-scope="scope">
            <i class="el-icon-user"></i>
            <span style="margin-left: 10px">{{ scope.row.passengerName }}</span>
          </template>
        </af-table-column>
        <af-table-column align="center" label="身份证号" width="200">
          <template slot-scope="scope">
            <i class="el-icon-key"></i>
            <span style="margin-left: 10px">{{ scope.row.passengerIdentity }}</span>
          </template>
        </af-table-column>
        <af-table-column align="center" label="手机号" width="180">
          <template slot-scope="scope">
            <i class="el-icon-mobile-phone"></i>
            <span style="margin-left: 10px">{{ scope.row.passengerPhone }}</span>
          </template>
        </af-table-column>
        <af-table-column align="center" label="资金流水" width="180">
          <template slot-scope="scope">
            <i class="el-icon-receiving"></i>
            <span style="margin-left: 10px;color:green">{{ scope.row.rechargeAccount }}</span>
          </template>
        </af-table-column>
        <af-table-column align="center" label="操作类型" width="180">
          <template slot-scope="scope">
            <i class="el-icon-set-up"></i>
            <span style="margin-left: 10px">{{ scope.row.optionName }}</span>
          </template>
        </af-table-column>
        <af-table-column align="center" label="操作">
          <template slot-scope="scope">
            <el-button size="mini" type="danger" @click="handleDelete(scope.$index, scope.row)">删除</el-button>
          </template>
        </af-table-column>
      </el-table>
    </div>
  </div>
</template>

<script>
export default {
  name: "CurrentFounds",
  data() {
    return {
      sumaccount: 0,
      RechargeList: []
    };
  },
  created() {
    this.getsumaccount();
    this.getRechargeList();
  },
  methods: {
    getsumaccount() {
      this.$axios
        .get("http://localhost:8087/wallentinfo/getsumaccount")
        .then(res => {
          this.sumaccount = res.data.result;
        });
    },
    getRechargeList() {
      this.$axios
        .get("http://localhost:8087/wallentinfo/getListByoptionName")
        .then(res => {
          this.RechargeList = res.data;
        });
		},
  }
};
</script>

<style>
</style>
