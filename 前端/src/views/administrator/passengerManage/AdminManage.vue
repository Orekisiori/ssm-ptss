<template>
  <div>
    <div>
      <el-row>
        <el-col :span="24">
          <div class="grid-content">
            <el-input
              placeholder="请输入内容"
              align="center"
              v-model="searchadmin.searchtext"
              style="width:200px"
              clearable
            ></el-input>
            <el-button icon="el-icon-search" @click="searchpassenger" circle></el-button>
          </div>
        </el-col>
      </el-row>
    </div>

    <!-- 所有管理员信息 -->
    <div style="margin-top:20px">
      <el-table :data="adminlist" style="width: 46%" border>
        <el-table-column align="center" label="管理员ID" width="180">
          <template slot-scope="scope">
            <el-tag type="success">{{ scope.row.adminId }}</el-tag>
          </template>
        </el-table-column>
        <el-table-column align="center" label="管理员名称" width="180">
          <template slot-scope="scope">
            <el-tag type="success">{{ scope.row.adminName }}</el-tag>
          </template>
        </el-table-column>
        <el-table-column align="center" label="管理员手机号" width="180">
          <template slot-scope="scope">
            <el-tag type="success">{{ scope.row.adminPhone }}</el-tag>
          </template>
        </el-table-column>
      </el-table>
    </div>

    <!-- 搜索列表 -->
    <div>
      <el-dialog title="查询结果" :visible.sync="centerDialogVisible" width="60%" center>
        <template>
          <el-table :data="searchList" style="width: 100%">
            <el-table-column align="center" prop="adminId" label="管理员ID" width="180"></el-table-column>
            <el-table-column align="center" prop="adminName" label="管理员名称" width="180"></el-table-column>
            <el-table-column align="center" prop="adminPhone" label="电话"></el-table-column>
          </el-table>
        </template>
      </el-dialog>
    </div>
  </div>
</template>

<script>
export default {
  name: "AdminManage",
  data() {
    return {
      searchadmin: {
        searchtext: ''
      },
      //保存所有管理员信息
      adminlist: [],
      searchList: [],
      centerDialogVisible:false
    }
  },
  created() {
    this.getList();
  },
  methods: {
    searchpassenger() {
      this.$axios.post("http://localhost:8087/register/searchByName",this.searchadmin)
        .then(res => {
          this.centerDialogVisible = true;
          this.searchList = res.data;
        })
    },
    getList() {
      this.$axios.get("http://localhost:8087/admininfo/getadminList")
        .then(res => {
          this.adminlist = res.data;
        })
    }
  }
}
</script>

<style>
</style>
