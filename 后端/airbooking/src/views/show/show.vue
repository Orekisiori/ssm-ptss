<template>
  <div class="show">
    <!-- 热门机票 -->
    <div class="Hotticket">
      <span style="font-size: 20px;color: #5DADE2;padding: 0;font-family: 'microsoft yahei'">热门机票</span>
      <div class="ticketwapper">
        <!-- 展示列表 -->
        <div class="ticketlist">
          <ul v-for="(item,index) in hotticketList" :key="index">
            <li>
              <img
                @click="showdetial(item.ticketId)"
                :src="item.ticketImg"
                width="250px"
                height="200px"
              />
              <span>{{item.ticketStartplace}}</span>
              <span>--</span>
              <span>{{item.ticketEndplace}}</span>
              <span style="margin-left:17px;color:red">¥{{item.ticketPrice}}</span>
            </li>
          </ul>
        </div>
      </div>
      <span style="float:right">
        <el-link @click="showmorehot">
          更多
          <i class="el-icon-more"></i>
        </el-link>
      </span>
    </div>

    <!-- 优惠机票 -->
    <div class="Discountticket">
      <span style="font-size: 20px;color: #F4D03F;padding: 0;font-family: 'microsoft yahei'">优惠机票</span>
      <div class="ticketwapper">
        <!-- 展示列表 -->
        <div class="ticketlist">
          <ul v-for="(item,index) in ticketList" :key="index">
            <img
              @click="showdetial(item.ticketId)"
              :src="item.ticketImg"
              width="250px"
              height="200px"
            />
            <li>
              <span>{{item.ticketStartplace}}</span>
              <span>--</span>
              <span>{{item.ticketEndplace}}</span>
              <span style="margin-left:17px;color:red">¥{{item.ticketPrice}}</span>
            </li>
          </ul>
        </div>
      </div>
      <span style="float:right">
        <el-link @click="showmorediscount">
          更多
          <i class="el-icon-more"></i>
        </el-link>
      </span>
    </div>

    <!-- 团购机票 -->
    <div class="Groupticket">
      <span style="font-size: 20px;color: #85929E;padding: 0;font-family: 'microsoft yahei'">团购机票</span>
      <div class="ticketwapper">
        <!-- 展示列表 -->
        <div class="ticketlist">
          <ul v-for="(item,index) in groupticketList" :key="index">
            <img
              @click="showdetial(item.ticketId)"
              :src="item.ticketImg"
              width="250px"
              height="200px"
            />
            <li>
              <span>{{item.ticketStartplace}}</span>
              <span>--</span>
              <span>{{item.ticketEndplace}}</span>
              <span style="margin-left:17px;color:red">¥{{item.ticketPrice}}</span>
            </li>
          </ul>
        </div>
      </div>
      <span style="float:right">
        <el-link @click="showmoregroup">
          更多
          <i class="el-icon-more"></i>
        </el-link>
      </span>
    </div>

    <!-- 订单详情dialog -->
    <div>
      <el-dialog
        title="机票详情"
        :visible.sync="dialogFormVisible"
        :close-on-click-modal="false"
        :close-on-press-escape="false"
        :modal-append-to-body="false"
      >
        <el-form>
          <el-form-item label="所属公司" :label-width="formLabelWidth">
            <el-tag type="success">{{detialTicketInfo.airlineName}}</el-tag>
          </el-form-item>
          <el-form-item label="出发地点" :label-width="formLabelWidth">
            <el-tag type="success">{{detialTicketInfo.ticketStartplace}}</el-tag>
          </el-form-item>
          <el-form-item label="到达地点" :label-width="formLabelWidth">
            <el-tag type="success">{{detialTicketInfo.ticketEndplace}}</el-tag>
          </el-form-item>
          <el-form-item label="出发时间" :label-width="formLabelWidth">
            <el-tag type="success">{{detialTicketInfo.ticketBegintime | date-format}}</el-tag>
          </el-form-item>
          <el-form-item label="预计到达时间" :label-width="formLabelWidth">
            <el-tag type="success">{{detialTicketInfo.ticketEndtime | date-format}}</el-tag>
          </el-form-item>
          <el-form-item label="乘机人" :label-width="formLabelWidth">
            <el-tag type="success">{{userform.passengerName}}</el-tag>
          </el-form-item>
          <el-form-item label="身份证号" :label-width="formLabelWidth">
            <el-tag type="success">{{userform.passengerIdentity}}</el-tag>
          </el-form-item>
          <el-form-item label="性别" :label-width="formLabelWidth">
            <el-tag type="success">{{userform.passengerGender}}</el-tag>
          </el-form-item>
          <el-form-item label="手机号" :label-width="formLabelWidth">
            <el-tag type="success">{{userform.passengerPhone}}</el-tag>
          </el-form-item>
          <el-form-item label="购买数量" :label-width="formLabelWidth">
            <el-input-number
              v-model="ticketOrderRequest.purchase_number"
              :min="1"
              :max="10"
              size="mini"
            ></el-input-number>
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button @click="dialogFormVisible = false">取 消</el-button>
          <el-button type="primary" @click="PostOrder">确 定</el-button>
        </div>
      </el-dialog>
    </div>

    <!-- 热门展示更多 -->
    <div>
      <el-dialog
        title
        :visible.sync="morehot"
        width="width"
      >
        <template>
          <el-table :data="hotticketList" style="width: 100%">
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
                <el-button size="mini" @click="handleOrder(scope.$index, scope.row)">购买</el-button>
              </template>
            </el-table-column>
          </el-table>
        </template>
      </el-dialog>
    </div>

    <!-- 优惠展示更多 -->
    <div>
      <el-dialog
        title
        :visible.sync="morediscount"
        width="width"
      >
        <template>
          <el-table :data="ticketList" style="width: 100%">
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
                <el-button size="mini" @click="handleOrder(scope.$index, scope.row)">购买</el-button>
              </template>
            </el-table-column>
          </el-table>
        </template>
      </el-dialog>
    </div>

    <!-- 团购展示更多 -->
    <div>
      <el-dialog
        title
        :visible.sync="moregroup"
        width="width"
      >
        <template>
          <el-table :data="groupticketList" style="width: 100%">
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
                <el-button size="mini" @click="handleOrder(scope.$index, scope.row)">购买</el-button>
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
  name: "Show",
  data() {
    return {
      ticketList: [],
      hotticketList: [],
      groupticketList: [],
      ticketIdform: {
        ticketId: null
      },
      detialTicketInfo: {},
      userform: {},
      tokenform: {
        username: localStorage.eleToken
      },
      dialogFormVisible: false,
      formLabelWidth: "120px",
      ticketOrderRequest: {
        ticketId: null,
        passengerId: null,
        purchase_number: 1
      },
      //展示更多
      morehot: false,
      morediscount: false,
      moregroup: false
    };
  },
  created() {
    var that = this;
    this.$axios.get("http://localhost:8087/ticketinfo/getlist").then(res => {
      this.ticketList = res.data;
      this.hotticketList = this.ticketList.concat();
      this.hotticketList.sort((a, b) => a.ticketNumber - b.ticketNumber);

      this.groupticketList = this.ticketList.concat();
      this.groupticketList.sort((a, b) => b.ticketNumber - a.ticketNumber);

      this.ticketList.sort((a, b) => a.ticketPrice - b.ticketPrice);
    });
  },
  methods: {
    showdetial(ticketId) {
      var that = this;
      that.ticketIdform.ticketId = ticketId;
      this.$axios
        .post("http://localhost:8087/ticketinfo/getDetial", that.ticketIdform)
        .then(res => {
          this.detialTicketInfo = res.data;
          this.dialogFormVisible = true;
          this.ticketOrderRequest.ticketId = res.data.ticketId;
        });
      this.$axios
        .post("http://localhost:8087/passengerinfo/current", that.tokenform)
        .then(res => {
          this.userform = res.data;
          this.ticketOrderRequest.passengerId = res.data.passengerId;
        });
    },
    PostOrder() {
      var that = this;
      this.$axios
        .post(
          "http://localhost:8087/orders/createorders",
          that.ticketOrderRequest
        )
        .then(res => {
          if (res.data == "余额充足，支付成功") {
            this.$message({
              message: res.data,
              type: "success"
            });
            this.dialogFormVisible = false;
          } else if (res.data == "机票不足") {
            this.$message({
              message: res.data,
              type: "warning"
            });
          } else if (res.data == "机票已售空") {
            this.$message({
              message: res.data,
              type: "warning"
            });
          } else if (res.data == "余额不足，请充值") {
            this.$message({
              message: res.data,
              type: "warning"
            });
            this.$router.push("/proflie/balance");
          } else {
            this.$message({
              message: res.data,
              type: "error"
            });
          }
        });
    },
    //展示更多
    showmorehot() {
      this.morehot = true;
    },
    handleOrder(index,row) {
      this.showdetial(row.ticketId);
    },
    showmorediscount() {
      this.morediscount = true;
    },
    showmoregroup() {
      this.moregroup = true;
    }
  }
};
</script>

<style scoped>
.show {
  padding: 0;
  margin: 0;
  width: 100%;
  height: auto;
  background-color: white;
}

.Hotticket {
  margin-top: 20px;
  padding: 0;
  margin: 0;
  width: 100%;
  height: 300px;
  background-color: #ffffff;
}

.Discountticket {
  padding: 0;
  margin: 0;
  width: 100%;
  height: 300px;
  background-color: #ffffff;
}

.Groupticket {
  padding: 0;
  margin: 0;
  width: 100%;
  height: 300px;
  background-color: #ffffff;
}

.ticketwapper {
  width: 97%;
  height: 270px;
  padding: 0;
  position: absolute;
  /* margin-top: 5px; */
  /* margin-left: 30px; */
  /* margin-right: 30px; */
  /* background-color: grey; */
  display: flex;
}

.ticketlist {
  width: 100%;
  height: 100%;
  overflow-y: hidden;
  overflow-x: auto;
  display: flex;
}

.ticketlist ul {
  padding: 0;
  margin: 20px 15px 0 0;
  width: 260px;
}

.ticketlist ul img {
  padding: 0;
  margin: 0;
  border-radius: 5px;
}

.ticketlist li {
  padding: 0;
  margin-bottom: 10px;
  font-size: 18px;
  justify-content: center;
  color: #99a9bf;
  height: 40px;
  display: flex;
  flex-wrap: wrap;
  align-items: center;
  width: 100%;
}

.ticketlist li span {
  padding: 0;
}
</style>
