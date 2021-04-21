<template>
  <div>
    <div>
      <el-row>
        <el-col :span="24">
          <div class="grid-content">
            <el-input
              placeholder="请输入内容"
              align="center"
              v-model="searchp.searchtext"
              style="width:200px"
              clearable
            ></el-input>
            <el-button icon="el-icon-search" @click="searchpassenger" circle></el-button>
          </div>
        </el-col>
      </el-row>
    </div>
    <div>
      <template>
        <el-table :data="plist" style="width: 100%">
          <el-table-column type="expand">
            <template slot-scope="props">
              <el-form label-position="left" inline class="demo-table-expand">
                <el-form-item label="用户名">
                  <span>{{ props.row.passengerName }}</span>
                </el-form-item>
                <el-form-item label="用户身份证">
                  <span>{{ props.row.passengerIdentity }}</span>
                </el-form-item>
                <el-form-item label="用户手机号">
                  <span>{{ props.row.passengerPhone }}</span>
                </el-form-item>
                <el-form-item label="用户性别">
                  <span>{{ props.row.passengerGender }}</span>
                </el-form-item>
                <el-form-item label="用户金额">
                  <span>{{ props.row.passengerAccount }}</span>
                </el-form-item>
                <el-form-item label="注册时间">
                  <span>{{ props.row.registerTime | date-format}}</span>
                </el-form-item>
              </el-form>
            </template>
          </el-table-column>
          <el-table-column label="用户名" prop="passengerName"></el-table-column>
          <el-table-column label="身份证" prop="passengerIdentity"></el-table-column>
          <el-table-column label="手机号" prop="passengerPhone"></el-table-column>
          <el-table-column label="操作">
            <template slot-scope="scope">
              <el-button size="mini" @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
              <el-button size="mini" type="danger" @click="handleDelete(scope.$index, scope.row)">删除</el-button>
            </template>
          </el-table-column>
        </el-table>
      </template>
    </div>

    <!-- 展示详情 -->
    <div>
      <el-dialog title="乘客详细信息" width="35%" :visible.sync="dialogFormVisible">
        <el-form :model="details" ref="details">
          <el-form-item label="乘客名">
            <el-input v-model="details.passengerName"></el-input>
          </el-form-item>
          <el-form-item label="身份证">
            <el-input v-model="details.passengerIdentity"></el-input>
          </el-form-item>
          <el-form-item label="手机号">
            <el-input v-model="details.passengerPhone"></el-input>
          </el-form-item>
        </el-form>
        <div slot="footer" align="center" class="dialog-footer">
          <el-button @click="dialogFormVisible = false">取 消</el-button>
          <el-button type="primary" @click="update">确 定</el-button>
        </div>
      </el-dialog>
    </div>

    <!-- 搜索列表 -->
    <div>
      <el-dialog title="提示" :visible.sync="centerDialogVisible" width="60%" center>
        <template>
          <el-table :data="searchbackinfo" style="width: 100%" :row-class-name="tableRowClassName">
            <el-table-column align="center" prop="passengerName" label="用户名" width="180"></el-table-column>
            <el-table-column align="center" prop="passengerIdentity" label="身份证" width="180"></el-table-column>
            <el-table-column align="center" prop="passengerPhone" label="手机号"></el-table-column>
            <el-table-column align="center" prop="passengerAccount" label="账户金额"></el-table-column>
          </el-table>
        </template>
      </el-dialog>
    </div>

  </div>
</template>


<script>
export default {
  name: "PassengerManage",
  data() {
    return {
      plist: [],
      dialogFormVisible: false,
      centerDialogVisible: false,
      form: {
      },
      details: {},
      searchp: {
        searchtext: ""
      },
      searchbackinfo: []
    };
  },
  created() {
    this.getPlist();
  },
  methods: {
    //获取列表
    getPlist() {
      this.$axios.get("http://localhost:8087/passengerinfo/info").then(res => {
        this.plist = res.data;
        this.plist.sort((a, b) => b.registerTime - a.registerTime);
      });
    },
    //编辑
    handleEdit(index, row) {
      this.dialogFormVisible = true;
      this.details = row;
    },
    //删除
    handleDelete(index, row) {
      this.$axios
        .post("http://localhost:8087/passengerinfo/deleteOne", row)
        .then(res => {
          this.$message({
            message: "删除成功",
            type: "success"
          });
          this.getPlist();
        });
    },
    //更新
    update() {
      this.$axios
        .post("http://localhost:8087/passengerinfo/updateinfo", this.details)
        .then(res => {
          this.$message({
            message: "信息更新成功",
            type: "success"
          });
          this.dialogFormVisible = false;
        });
    },
    //搜索
    searchpassenger() {
      this.$axios
        .post("http://localhost:8087/passengerinfo/searchByName", this.searchp)
        .then(res => {
          this.centerDialogVisible = true;
          this.searchbackinfo = res.data;
        });
    },
    //颜色背景显示
    tableRowClassName({ row, rowIndex }) {
      if (rowIndex === 1) {
        return "warning-row";
      } else if (rowIndex === 3) {
        return "success-row";
      }
      return "";
    },
  }
};
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
.grid-content {
  border-radius: 4px;
  min-height: 36px;
}
</style>
