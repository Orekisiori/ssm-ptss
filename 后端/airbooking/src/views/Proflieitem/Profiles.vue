<template>
  <div>
    <div class="profileform">
      <el-form ref="form" :model="form" label-width="80px" style="width:350px" :rules="rules">
        <el-form-item label="姓名" prop="passengerName">
          <el-input v-model="form.passengerName" disabled></el-input>
        </el-form-item>
        <el-form-item label="性别" prop="passengerGender">
          <el-radio-group v-model="form.passengerGender">
            <el-radio label="男"></el-radio>
            <el-radio label="女"></el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="身份证号" prop="passengerIdentity">
          <el-input v-model="form.passengerIdentity" maxlength="18"></el-input>
        </el-form-item>
        <el-form-item label="手机号" prop="passengerPhone">
          <el-input v-model="form.passengerPhone" maxlength="11"></el-input>
        </el-form-item>
        <el-form-item class="modifybtn">
          <el-button type="primary" @click="updateinfo('form')" round>更新信息</el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script>
export default {
  name: "Profiles",
  data() {
    return {
      imageUrl: "",
      form: {},
      tokenform: {
        username: localStorage.eleToken
      },
      rules: {
        passengerName: [
          {
            required: true,
            message: "用户名不能为空",
            trigger: "blur"
          },
          {
            min: 2,
            max: 12,
            message: "长度在2-12之间",
            trigger: "blur"
          }
        ],
        passengerIdentity: [
          {
            required: true,
            message: "身份证号不能为空",
            trigger: "blur"
          },
          {
            pattern: /^[1-9]\d{7}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}$|^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}([0-9]|X)$/,
            message: "请输入正确的身份证号",
            trigger: "blur"
          }
        ],
        passengerPhone: [
          {
            required: true,
            message: "手机号不能为空",
            trigger: "blur"
          },
          {
            pattern: /^[1](([3][0-9])|([4][5-9])|([5][0-3,5-9])|([6][5,6])|([7][0-8])|([8][0-9])|([9][1,8,9]))[0-9]{8}$/,
            message: "请输入正确的手机号",
            trigger: "blur"
          }
        ]
      }
    };
  },
  methods: {
    updateinfo(formName) {
      this.$refs[formName].validate(vaild => {
        if (vaild) {
          var that = this;
          this.$axios
            .post("http://localhost:8087/passengerinfo/updateinfo", that.form)
            .then(res => {
              this.$message({
                message: "信息更新成功",
                type: "success"
              });
            });
        } else {
          this.$message({
            message: "请输入正确的信息",
          });
        }
      });
    }
  },
  created() {
    var that = this;
    this.$axios
      .post("http://localhost:8087/passengerinfo/current", that.tokenform)
      .then(res => {
        that.form = res.data;
      });
    // this.$axios.get()
  }
};
</script>

<style scoped>
.avatar-uploader .el-upload {
  border: 1px dashed #d9d9d9;
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
}

.avatar-uploader .el-upload:hover {
  border-color: #409eff;
}

.avatar-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 178px;
  height: 178px;
  line-height: 178px;
  text-align: center;
}

.avatar {
  width: 178px;
  height: 178px;
  display: block;
}

.profileform {
  width: 40%;
  height: auto;
  background-color: white;
  align-items: center;
  justify-content: center;
}

.modifybtn {
  text-align: center;
}
</style>
