<template>
  <div>
    <div>
      <template>
        <el-table :data="currentTicketList" style="width: 100%">
          <el-table-column type="expand">
            <template slot-scope="props">
              <el-form label-position="left" inline class="demo-table-expand">
                <el-form-item label="公司名">
                  <span>{{ props.row.airlineName }}</span>
                </el-form-item>
                <el-form-item label="出发地">
                  <span>{{ props.row.ticketStartplace}}</span>
                </el-form-item>
                <el-form-item label="目的地">
                  <span>{{ props.row.ticketEndplace}}</span>
                </el-form-item>
                <el-form-item label="出发时间">
                  <span>{{ props.row.ticketBegintime | date-format }}</span>
                </el-form-item>
                <el-form-item label="预计到达时间">
                  <span>{{ props.row.ticketEndtime | date-format }}</span>
                </el-form-item>
                <el-form-item label="机票价格">
                  <span>{{ props.row.ticketPrice}}</span>
                </el-form-item>
                <el-form-item label="余票数">
                  <span>{{ props.row.ticketNumber}}</span>
                </el-form-item>
              </el-form>
            </template>
          </el-table-column>
          <el-table-column label="机票ID" prop="ticketId"></el-table-column>
          <el-table-column label="余票数" prop="ticketNumber"></el-table-column>
          <el-table-column label="机票价格" prop="ticketPrice"></el-table-column>
          <el-table-column label="操作">
            <template slot-scope="scope">
              <el-button size="mini" @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
              <el-button size="mini" type="danger" @click="handleDelete(scope.$index, scope.row)">删除</el-button>
            </template>
          </el-table-column>
        </el-table>
      </template>
    </div>

    <!-- 展示编辑详情 -->
    <div>
      <el-dialog title="编辑机票" :visible.sync="centerDialogVisible" width="50%" center>
        <span slot="footer" class="dialog-footer">
          <el-form :model="detailedit" ref="detailedit">
            <el-form-item>
              <el-image :src="detailedit.ticketImg"></el-image>
            </el-form-item>
            <el-form-item label="出发地">
              <el-input v-model="detailedit.ticketStartplace"></el-input>
            </el-form-item>
            <el-form-item label="目的地">
              <el-input v-model="detailedit.ticketEndplace"></el-input>
            </el-form-item>
            <el-form-item label="出发">
              <el-date-picker
                v-model="detailedit.ticketBegintime"
                type="date"
                placeholder="选择日期"
                :picker-options="startdateop"
              ></el-date-picker>
            </el-form-item>
            <el-form-item label="出发">
              <el-date-picker
                v-model="detailedit.ticketEndtime"
                type="date"
                placeholder="选择日期"
                :picker-options="startdateop"
              ></el-date-picker>
            </el-form-item>
            <el-form-item label="机票价格">
              <el-input v-model="detailedit.ticketPrice"></el-input>
            </el-form-item>
          </el-form>
          <div slot="footer" align="center" class="dialog-footer">
            <el-button @click="centerDialogVisible = false">取 消</el-button>
            <el-button type="primary" @click="updateticket">确 定</el-button>
          </div>
        </span>
      </el-dialog>
    </div>
  </div>
</template>

<script>
//获取前一天的日期
let time = new Date().getTime() - 24 * 60 * 60 * 1000;
let yesterday = new Date(time);
export default {
  name: "Aticketmanage",
  data() {
    return {
      tokenform: {
        airlineName: localStorage.eleToken
      },
      currentTicketList: [],
      centerDialogVisible: false,
      detailedit: {},

      //判断所选日期是否大于前一天的日期来决定是否可选
      startdateop: {
        disabledDate: time => {
          return time.getTime() < yesterday;
        }
      },
      //判断选择的日期是否大于或等于开始日期
      enddateop: {
        disabledDate: time => {
          return time.getTime() < this.detailedit.ticketBegintime;
        }
      },
    };
  },
  created() {
    this.getCurrentticketList();
  },
  methods: {
    getCurrentticketList() {
      this.$axios
        .post("http://localhost:8087/ticketinfo/getCurrentList", this.tokenform)
        .then(res => {
          this.currentTicketList = res.data;
        });
    },
    handleEdit(index, row) {
      this.centerDialogVisible = true;
      this.detailedit = row;
    },
    handleDelete(index, row) {
      console.log(row);
    },
    updateticket() {
      this.$axios.post("http://localhost:8087/ticketinfo/updateTicket",this.detailedit)
        .then (res => {
          this.$message({
            message: "信息更新成功",
            type: "success"
          });
          this.centerDialogVisible = false;
        })
    }
  }
};
</script>

<style scoped>
</style>
