<template>
  <div>
    <!-- 搜索 -->
    <div>
      <el-row>
        <el-col :span="24">
          <div class="grid-content">
            <el-input
              placeholder="请输入内容"
              align="center"
              v-model="searchrecord.searchtext"
              style="width:200px"
              clearable
            ></el-input>
            <el-button icon="el-icon-search" @click="searchrecordList" circle></el-button>
          </div>
        </el-col>
      </el-row>
    </div>

    <div style="margin-top:20px">
      <el-table :data="recoedList" algin="center" style="width: 61%" border>
        <el-table-column align="center" label="管理员ID" width="180">
          <template slot-scope="scope">
            <el-tag type="success">{{ scope.row.adminId }}</el-tag>
          </template>
        </el-table-column>
        <el-table-column align="center" label="管理员名" width="180">
          <template slot-scope="scope">
            <el-tag type="success">{{ scope.row.adminName }}</el-tag>
          </template>
        </el-table-column>
        <el-table-column align="center" label="手机号" width="180">
          <template slot-scope="scope">
            <el-tag type="success">{{ scope.row.adminPhone }}</el-tag>
          </template>
        </el-table-column>
        <el-table-column align="center" label="登录时间" width="180">
          <template slot-scope="scope">
            <el-tag type="success">{{ scope.row.logTime | date-format}}</el-tag>
          </template>
        </el-table-column>
      </el-table>
    </div>

    <!-- 搜索结果 -->
    <!-- 搜索列表 -->
    <div>
      <el-dialog title="查询结果" :visible.sync="recordresult" width="53.6%" center>
        <template>
          <el-table :data="searchResult" algin="center" width="100%" border>
            <el-table-column align="center" label="管理员ID" width="180">
              <template slot-scope="scope">
                <el-tag type="success">{{ scope.row.adminId }}</el-tag>
              </template>
            </el-table-column>
            <el-table-column align="center" label="管理员名" width="180">
              <template slot-scope="scope">
                <el-tag type="success">{{ scope.row.adminName }}</el-tag>
              </template>
            </el-table-column>
            <el-table-column align="center" label="手机号" width="180">
              <template slot-scope="scope">
                <el-tag type="success">{{ scope.row.adminPhone }}</el-tag>
              </template>
            </el-table-column>
            <el-table-column align="center" label="登录时间" width="180">
              <template slot-scope="scope">
                <el-tag type="success">{{ scope.row.logTime | date-format}}</el-tag>
              </template>
            </el-table-column>
          </el-table>
        </template>
      </el-dialog>
    </div>
  </div>
</template>

<script>
export default {
  name: "LoginLog",
  data() {
    return {
      recoedList: [],
      searchrecord: {
        searchtext: ""
      },
      searchResult: [],
      recordresult: false
    };
  },
  created() {
    this.getList();
  },
  methods: {
    getList() {
      this.$axios.get("http://localhost:8087/logrecord/getList").then(res => {
				this.recoedList = res.data;
				this.recoedList.sort((a,b) => b.logTime - a.logTime);
      });
    },

    //搜索
    searchrecordList() {
      this.$axios
        .post("http://localhost:8087/logrecord/searchList", this.searchrecord)
        .then(res => {
					this.searchResult = res.data;
					this.recordresult = true;
        });
    },

    //珊格颜色
    tableRowClassName({ row, rowIndex }) {
      if (rowIndex === 1) {
        return "warning-row";
      } else if (rowIndex === 3) {
        return "success-row";
      }
      return "";
    }
  }
};
</script>

<style scoped>
.el-table .warning-row {
  background: oldlace;
}

.el-table .success-row {
  background: #f0f9eb;
}
</style>
