<template>
  <div>
    <div style="margin-top:10px">
      <el-table :data="ApplicateList" style="width: 100%;" stripe show-overflow-tooltip="true">
        <af-table-column align="center" label="公司ID" width="190">
          <template slot-scope="scope">
            <i class></i>
            <span style="margin-left: 10px">{{ scope.row.airlineId}}</span>
          </template>
        </af-table-column>
        <af-table-column align="center" label="公司名称" width="150">
          <template slot-scope="scope">
            <i class="el-icon-user"></i>
            <span style="margin-left: 10px">{{ scope.row.airlineName }}</span>
          </template>
        </af-table-column>
        <af-table-column align="center" label="纳税人识别号" width="200">
          <template slot-scope="scope">
            <i class="el-icon-key"></i>
            <span style="margin-left: 10px">{{ scope.row.airlineIdentity }}</span>
          </template>
        </af-table-column>
        <af-table-column align="center" label="申请金额" width="180">
          <template slot-scope="scope">
            <i class="el-icon-receiving"></i>
            <span style="margin-left: 10px;color:red">{{ scope.row.requestAccount }}</span>
          </template>
        </af-table-column>
        <af-table-column align="center" label="状态" width="180">
          <template slot-scope="scope">
            <i class="el-icon-set-up"></i>
            <span style="margin-left: 10px">{{ scope.row.applicateStatus }}</span>
          </template>
        </af-table-column>
        <af-table-column align="center" label="操作">
          <template slot-scope="scope">
            <el-button size="mini" type="danger" @click="handleresult(scope.$index, scope.row)">审核通过</el-button>
          </template>
        </af-table-column>
      </el-table>
    </div>
  </div>
</template>

<script>
export default {
  name: "WithdrawApplication",
  data() {
    return {
      ApplicateList: []
    };
  },
  created() {
    this.getApplicateList();
  },
  methods: {
    getApplicateList() {
      this.$axios
        .get("http://localhost:8087/applicate/getApplicateList")
        .then(res => {
          this.ApplicateList = res.data;
        });
    },
    handleresult(index, row) {
      this.$axios
        .post("http://localhost:8087/applicate/rebackapplicate", row)
        .then(res => {
          if (res.data == "已审核，请勿再次申请") {
            this.$message({
              message: res.data,
              type: "warning"
            });
					}
					else if (res.data == "审核成功") {
						this.$message({
              message: res.data,
              type: "success"
						});
						this.getApplicateList();
					}
					else {
						this.$message({
              message: "未知错误",
              type: "error"
            });
					}
        });
    }
  }
};
</script>

<style>
</style>
