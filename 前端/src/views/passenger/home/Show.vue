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
                :src="imgUrl(item.ticketImg)"
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
              :src="imgUrl(item.ticketImg)"
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
              :src="imgUrl(item.ticketImg)"
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
            <el-tag type="success">{{detialTicketInfo.ticketBegintime | moment}} (UTC+8)</el-tag>
          </el-form-item>
          <el-form-item label="预计到达时间" :label-width="formLabelWidth">
            <el-tag type="success">{{detialTicketInfo.ticketEndtime | moment}} (UTC+8)</el-tag>
          </el-form-item>
          <el-form-item label="乘机人" :label-width="formLabelWidth">
            <el-tag type="success">{{userform.passengerName}}</el-tag>
          </el-form-item>
          <el-form-item label="机舱选择" :label-width="formLabelWidth">
          <el-select v-model="ticketOrderRequest.cabin" placeholder="请选择">
            <el-option
              v-for="item in options1"
              :key="item.value"
              :label="item.label"
              :value="item.value">
            </el-option>
          </el-select>
        </el-form-item>
          <el-form-item label="座位选择" :label-width="formLabelWidth">
            <el-select v-model="ticketOrderRequest.seat" placeholder="请选择">
              <el-option
                v-for="item in options2"
                :key="item.value"
                :label="item.label"
                :value="item.value">
              </el-option>
            </el-select>
          </el-form-item>
<!--          <el-form-item label="购买数量" :label-width="formLabelWidth">
            <el-input-number
              v-model="ticketOrderRequest.purchase_number"
              :min="1"
              :max="1"
              size="mini"
            ></el-input-number>
          </el-form-item>-->
          <el-form-item label="机票售价" :label-width="formLabelWidth">
            <el-tag>{{detialTicketInfo.ticketPrice}}￥</el-tag>
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
                    <span>{{ props.row.ticketBegintime | moment }}</span>
                  </el-form-item>
                  <el-form-item label="预计到达时间">
                    <span>{{ props.row.ticketEndtime | moment }}</span>
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
                    <span>{{ props.row.ticketBegintime | moment }}</span>z
                  </el-form-item>
                  <el-form-item label="预计到达时间">
                    <span>{{ props.row.ticketEndtime | moment }}</span>
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
                    <span>{{ props.row.ticketBegintime | moment }}</span>
                  </el-form-item>
                  <el-form-item label="预计到达时间">
                    <span>{{ props.row.ticketEndtime | moment }}</span>
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
    let Img;

    return {
      ticketList: [],
      hotticketList: [],
      groupticketList: [],
      ticketIdform: {
        ticketId: null
      },
      detialTicketInfo: {
        ticketId: null,
        airlineId: null,
        airlineName: null,
        ticketStartplace: null,
        ticketEndplace: null,
        ticketBegintime: null,
        ticketEndtime: null,
        ticketNumber: null,
        ticketPrice: null,
        ticketImg: null
      },
      userform: {},
      tokenform: {
        username: localStorage.eleToken
      },
      dialogFormVisible: false,
      formLabelWidth: "120px",
      ticketOrderRequest: {
        ticketId: null,
        passengerId: null,
        purchase_number: 1,
        cabin: null,
        seat: null
      },
      //展示更多
      morehot: false,
      morediscount: false,
      moregroup: false,
      options1: [{
        value: '1',
        label: '1号舱'
      }, {
        value: '2',
        label: '2号舱'
      }, {
        value: '3',
        label: '3号舱'
      }, {
        value: '4',
        label: '4号舱'
      }, {
        value: '5',
        label: '5号舱'
      }, {
        value: '6',
        label: '6号舱'
      }],

      options2: [{
        value: '1a',
        label: '1a'
      }, {
        value: '1b',
        label: '1b'
      }, {
        value: '1c',
        label: '1c'
      }, {
        value: '2a',
        label: '2a'
      }, {
        value: '2b',
        label: '2b'
      }, {
        value: '2c',
        label: '2c'
      }, {
        value: '3a',
        label: '3a'
      }, {
        value: '3b',
        label: '3b'
      }, {
        value: '3c',
        label: '3c'
      }],
    };
  },
  created() {
    const that = this;
    this.$axios.post("http://localhost:8181/ticket/getList").then(res => {
      console.log(res.data);
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
      const that = this;
      that.ticketIdform.ticketId = ticketId;
      this.$axios
        .post("http://localhost:8181/ticket/getDetial/"+that.ticketIdform.ticketId)
        .then(res => {
          this.detialTicketInfo = res.data;
          this.dialogFormVisible = true;
          this.ticketOrderRequest.ticketId = res.data.ticketId;
        });
      this.$axios
        .post("http://localhost:8181/passenger/current/"+that.tokenform.username)
        .then(res => {
          this.userform = res.data;
          this.ticketOrderRequest.passengerId = res.data.passengerId;
        });
    },
    PostOrder() {
      const that = this;
      this.$axios
        .post(
          "http://localhost:8181/order/createorder/"
          +that.ticketOrderRequest.ticketId+"/"
          +that.ticketOrderRequest.passengerId+"/"
          +that.ticketOrderRequest.purchase_number+"/"
          +that.ticketOrderRequest.cabin+"-"+that.ticketOrderRequest.seat
        )
        .then(res => {
          if (res.data === "余额充足，支付成功") {
            this.$message({
              message: res.data,
              type: "success"
            });
            this.dialogFormVisible = false;
          } else if (res.data === "机票不足") {
            this.$message({
              message: res.data,
              type: "warning"
            });
          } else if (res.data === "机票已售空") {
            this.$message({
              message: res.data,
              type: "warning"
            });
          } else if (res.data === "余额不足，请充值") {
            this.$message({
              message: res.data,
              type: "warning"
            });
            this.$router.push("/proflie/balance");
          } else if(res.data === "已选"){
            this.$message({
              message: '抱歉，该座已售出，请选择其他座位',
              type: "warning"
            });
          }
          else {
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
      // console.log("hanleOrder");
      this.showdetial(row.ticketId);
      // console.log(row.ticketId);
    },
    showmorediscount() {
      this.morediscount = true;
    },
    showmoregroup() {
      this.moregroup = true;
    },
    imgUrl(img){
      return require("../../../assets/img/" + img);
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
  /*margin: 0;*/
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
