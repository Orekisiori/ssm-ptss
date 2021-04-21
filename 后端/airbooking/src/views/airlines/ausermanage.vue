<template>
  <div>
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
  </div>
</template>

<script>
export default {
  name: "Ausermanage",
  data() {
    return {
      dialogFormVisible: false,
      plist: [],
      details: {}
    };
  },
  created() {
    this.getPlist();
  },
  methods: {
    getPlist() {
      this.$axios.get("http://localhost:8087/passengerinfo/info").then(res => {
        this.plist = res.data;
        this.plist.sort((a, b) => b.registerTime - a.registerTime);
      });
    },
    handleEdit(index, row) {
      this.dialogFormVisible = true;
      this.details = row;
    },
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
    }
  }
};
</script>

<style scoped>
</style>
