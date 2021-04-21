<template>
  <div>
    <!-- 左侧 -->
    <div class="left">
      <template>
        <el-table :data="tList" style="width: 100%">
          <el-table-column label="机票ID">
            <template slot-scope="scope">
              <i class="el-icon-time"></i>
              <span style="margin-left: 10px">{{ scope.row.airlineId }}</span>
            </template>
          </el-table-column>
          <el-table-column label="出发地">
            <template slot-scope="scope">
              <i class="el-icon-time"></i>
              <span style="margin-left: 10px">{{ scope.row.ticketStartplace }}</span>
            </template>
          </el-table-column>
          <el-table-column label="目的地">
            <template slot-scope="scope">
              <i class="el-icon-time"></i>
              <span style="margin-left: 10px">{{ scope.row.ticketEndplace }}</span>
            </template>
          </el-table-column>
          <el-table-column label="价格">
            <template slot-scope="scope">
              <i class="el-icon-time"></i>
              <span style="margin-left: 10px">{{ scope.row.ticketPrice }}</span>
            </template>
          </el-table-column>
          <el-table-column label="操作">
            <template slot-scope="scope">
              <el-button
                size="mini"
                type="danger"
                @click="addtouser(scope.$index, scope.row)"
              >添加至主界面</el-button>
            </template>
          </el-table-column>
        </el-table>
      </template>
    </div>

		<!-- 右侧 -->
		<div class="right">

		</div>
  </div>
</template>

<script>
export default {
  name: "PageSetting",
  data() {
    return {
      tList: []
    };
  },
  created() {
    this.getlist();
  },
  methods: {
    getlist() {
      this.$axios.get("http://localhost:8087/ticketinfo/getlist").then(res => {
        this.tList = res.data;
      });
    },
    addtouser(index, row) {
      // this.$axios
      //   .post("http://localhost:8087/ticketinfo/addtuShowtable", row)
      //   .then(res => {
      //     this.$message({
      //       message: "添加成功",
      //       type: "success"
      //     });
      //   });
		},
		
		//显示展示在前端的机票id
		showUser() {
			this.get()
		}
  }
};
</script>

<style scoped>
.left {
  width: 50%;
  height: auto;
  float: left;
}

.right {
	width: 50%;
	height: auto;
	float: left;
}
</style>
