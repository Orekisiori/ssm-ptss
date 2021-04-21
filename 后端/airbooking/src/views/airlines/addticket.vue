<template>
  <div>
    <div style="width:500px;align:center">
      <el-form :model="ticketInfo" ref="ticketInfo" label-width="100px" :rules="rules">
        <el-form-item>
          <el-upload
            action="https://jsonplaceholder.typicode/posts"
            list-type="picture-card"
            :on-remove="handleRemove"
            :class="{hide:hideUpload}"
            :on-change="handleChange"
          >
            <i class="el-icon-plus avatar-uploader-icon"></i>
          </el-upload>
        </el-form-item>
        <el-form-item label="出发地" prop="ticketStartplace">
          <el-input v-model="ticketInfo.ticketStartplace" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="目的地" prop="ticketEndplace">
          <el-input v-model="ticketInfo.ticketEndplace" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="出发">
          <el-date-picker
            v-model="ticketInfo.ticketBegintime"
            type="date"
            placeholder="选择日期"
            style="width: 390px;"
            :picker-options="startdateop"
          ></el-date-picker>
        </el-form-item>
        <el-form-item label="归程">
          <el-date-picker
            v-model="ticketInfo.ticketEndtime"
            type="date"
            placeholder="选择日期"
            style="width: 390px;"
            :picker-options="enddateop"
          ></el-date-picker>
        </el-form-item>
        <el-form-item label="机票数量" prop="ticketNumber">
          <el-input v-model="ticketInfo.ticketNumber" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item label="单价" prop="ticketPrice">
          <el-input v-model="ticketInfo.ticketPrice" autocomplete="off"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="submitForm('ticketInfo')">提交</el-button>
          <el-button @click="resetForm('ticketInfo')">重置</el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script>
//获取前一天的日期
let time = new Date().getTime() - 24 * 60 * 60 * 1000;
let yesterday = new Date(time);
export default {
  name: "AddTicket",
  data() {
    return {
      hideUpload: false,
      limitCount: 1,

      ticketInfo: {
        airlineId: null,
        airlineName: null,
        ticketStartplace: null,
        ticketEndplace: null,
        ticketImg: null,
        ticketBegintime: null,
        ticketEndtime: null
      },
      rules: {
        ticketStartplace: [
          {
            required: true,
            message: "出发地不能为空",
            trigger: "blur"
          }
        ],
        ticketEndplace: [
          {
            required: true,
            message: "目的地不能为空",
            trigger: "blur"
          }
        ]
      },

      //判断所选日期是否大于前一天的日期来决定是否可选
      startdateop: {
        disabledDate: time => {
          return time.getTime() < yesterday;
        }
      },
      //判断选择的日期是否大于或等于开始日期
      enddateop: {
        disabledDate: time => {
          return time.getTime() < this.ticketInfo.ticketBegintime;
        }
      },
      tokenform: {
        searchtext: localStorage.eleToken
      }
    };
  },
  created() {
    this.getairlineId();
  },
  methods: {
    handleRemove(file, filelist) {
      this.hideUpload = filelist.length >= this.limitCount;
    },
    handleChange(file, fileList) {
      file = file.raw;

      const isJPG =
        file.type === "image/jpeg" ||
        file.type === "image/jpg" ||
        file.type === "image/png";

      const isLt2M = file.size / 1024 / 1024 < 2;

      if (!isJPG) {
        this.$message.error("上传图片只能是JPG或PNG格式！");
      }
      if (!isLt2M) {
        this.$message.error("上传图片大小不能超过2MB！");
      }
      if (isJPG && isLt2M) {
        const fd = new FormData();
        fd.append("file", file);
        //预览本地图
        const reader = new FileReader();
        reader.readAsDataURL(file);
        reader.onload = event => {
          this.ticketInfo.ticketImg = event.target.result;
        };
      }
      this.hideUpload = fileList.length >= this.limitCount;
    },

    submitForm(formName) {
      this.$refs[formName].validate(valid => {
        if (valid) {
          this.$axios
            .post("http://localhost:8087/ticketinfo/addticket", this.ticketInfo)
            .then(res => {
              this.$message({
                message: "机票添加成功",
                type: "success"
              });
            });
        }
      });
    },
    resetForm(formName) {
      this.$refs[formName].resetFields();
    },

    //获取当前航空公司信息
    getairlineId() {
      this.$axios
        .post("http://localhost:8087/airlineInfo/searchByName", this.tokenform)
        .then(res => {
          this.ticketInfo.airlineId = res.data[0].airlineId;
          this.ticketInfo.airlineName = res.data[0].airlineName;
        });
    }
  }
};
</script>

<style scoped>
.hide .el-upload--picture-card {
  display: none;
}
</style>