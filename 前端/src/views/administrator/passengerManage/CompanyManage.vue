<template>
  <div>
    <div>
      <el-row>
        <el-col :span="24">
          <div class="grid-content">
            <el-input
              placeholder="请输入内容"
              align="center"
              v-model="searchcompany.searchtext"
              style="width:200px"
              clearable
            ></el-input>
            <el-button icon="el-icon-search" @click="searchairline" circle></el-button>
          </div>
        </el-col>
      </el-row>
    </div>

    <div style="margin-top:20px">
      <el-table :data="Clist" style="width: 92.5%" border>
        <el-table-column align="center" label="公司名" width="180">
          <template slot-scope="scope">
            <el-tag type="success">{{ scope.row.airlineName }}</el-tag>
          </template>
        </el-table-column>
        <el-table-column align="center" label="纳税人识别号" width="180">
          <template slot-scope="scope">
            <el-tag type="success">{{ scope.row.airlineIdentity }}</el-tag>
          </template>
        </el-table-column>
        <el-table-column align="center" label="注册时间" width="180">
          <template slot-scope="scope">
            <el-tag type="success">{{ scope.row.registerdate | date-format }}</el-tag>
          </template>
        </el-table-column>
        <el-table-column align="center" label="账户金额" width="180">
          <template slot-scope="scope">
            <el-tag type="success">{{ scope.row.airlineAccount }}</el-tag>
          </template>
        </el-table-column>
        <el-table-column align="center" label="审核结果" width="180">
          <template slot-scope="scope">
            <el-tag type="success">{{ scope.row.isvalid }}</el-tag>
          </template>
        </el-table-column>
        <el-table-column align="center" label="操作" width="180">
          <template slot-scope="scope">
            <el-button size="mini" @click="handleEdit(scope.$index, scope.row)">审核</el-button>
            <el-button size="mini" type="danger" @click="handleDelete(scope.$index, scope.row)">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>

    <!-- 审核公司资料 -->
    <div>
      <el-dialog title="审核公司资料" :visible.sync="dialogVisible" width="40%">
        <div>
          <el-form>
            <el-form-item label="公司名">
              <el-tag type="success">{{vaildpass.airlineName}}</el-tag>
            </el-form-item>
            <el-form-item label="纳税人识别号">
              <el-tag type="success">{{vaildpass.airlineIdentity}}</el-tag>
            </el-form-item>
            <el-form-item label="公司电话">
              <el-tag type="success">{{vaildpass.airlinePhone}}</el-tag>
            </el-form-item>
            <el-form-item label="注册时间">
              <el-tag type="success">{{vaildpass.registerdate | date-format}}</el-tag>
            </el-form-item>
            <el-form-item label="账户金额">
              <el-tag type="success">{{vaildpass.airlineAccount}}</el-tag>
            </el-form-item>
            <el-form-item label="审核结果">
              <el-select v-model="vaildpass.isvalid" placeholder="请选择">
                <el-option
                  v-for="item in options"
                  :key="item.value"
                  :label="item.label"
                  :value="item.value"
                ></el-option>
              </el-select>
            </el-form-item>
          </el-form>
        </div>
        <div slot="footer">
          <el-button @click="dialogVisible = false">取 消</el-button>
          <el-button type="primary" @click="postvaild">确 定</el-button>
        </div>
      </el-dialog>
    </div>

    <!-- 搜索列表 -->
    <div>
      <el-dialog title="查询结果" :visible.sync="centerDialogVisible" width="60%" center>
        <template>
          <el-table :data="searchbackinfo" style="width: 100%">
            <el-table-column align="center" prop="airlineName" label="公司名称" width="180"></el-table-column>
            <el-table-column align="center" prop="airlineIdentity" label="纳税人识别号" width="180"></el-table-column>
            <el-table-column align="center" prop="airlinePhone" label="电话"></el-table-column>
            <el-table-column align="center" prop="airlineAccount" label="账户金额"></el-table-column>
          </el-table>
        </template>
      </el-dialog>
    </div>

  </div>
</template>

<script>
export default {
  name: "CompanyManage",
  data() {
    return {
      Clist: [],
      vaildpass: {},
      //展示是否详细供审核
      dialogVisible: false,
      centerDialogVisible:false,
      options: [
        {
          value: "未审核",
          label: "未审核"
        },
        {
          value: "审核通过",
          label: "审核通过"
        }
      ],
      searchcompany: {
        searchtext: ""
      },
      searchbackinfo: []
    };
  },
  created() {
    this.gitClist();
  },
  methods: {
    gitClist() {
      this.$axios.get("http://localhost:8087/airlineInfo/getList").then(res => {
        this.Clist = res.data;
      });
    },
    handleEdit(index, row) {
      this.vaildpass = row;
      this.dialogVisible = true;
    },
    handleDelete(index, row) {
      this.$axios
        .post("http://localhost:8087/company/deleteOne", row)
        .then(res => {
          if (res.data == "删除成功") {
            this.$message({
              message: "修改成功",
              type: "success"
            });
            this.gitClist();
          }
          else {
            this.$message({
            message: "未知错误",
            type: "error"
          });
          }
        });
    },
    postvaild() {
      this.$axios
        .post("http://localhost:8087/company/validpass", this.vaildpass)
        .then(res => {
          if (res.data == "审核通过") {
            this.$message({
              message: "修改成功",
              type: "success"
            });
            this.dialogVisible = false;
          } else {
            this.$message({
              message: "未知错误",
              type: "error"
            });
          }
        });
    },
    //搜索公司
    searchairline() {
      this.$axios.post("http://localhost:8087/airlineInfo/searchByName",this.searchcompany)
        .then(res => {
          this.centerDialogVisible = true;
          this.searchbackinfo = res.data;
        })
    }
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
</style>
