<template>
	<div class="wapper">
		<el-container>
			<el-header></el-header>
			<el-main>
				<el-form ref="airlineInfo" :model="airlineInfo" :rules="rules" label-width="100px" style="width: 400px;">
					<el-form-item label="公司名称" prop="airlineName">
						<el-input v-model="airlineInfo.airlineName"></el-input>
					</el-form-item>
					<el-form-item label="密码" prop="airlinePassword">
						<el-input type="password"  v-model="airlineInfo.airlinePassword"></el-input>
					</el-form-item>
					<el-form-item label="确认密码" prop="airlinePassword2">
						<el-input type="password"  v-model="airlineInfo.airlinePassword2"></el-input>
					</el-form-item>
					<el-form-item label="企业识别号" prop="airlineIdentity">
						<el-input v-model="airlineInfo.airlineIdentity"></el-input>
					</el-form-item>
					<el-form-item label="手机号" prop="airlinePhone">
						<el-input v-model="airlineInfo.airlinePhone" maxlength="11"></el-input>
					</el-form-item>
					<el-form-item style="margin-left: 46px;">
						<el-button type="success" @click="submitFrom('airlineInfo')" round>注册</el-button>
						<el-button type="danger" @click="reback" round>返回</el-button>
					</el-form-item>
				</el-form>
			</el-main>
			<el-footer></el-footer>
		</el-container>
	</div>
</template>

<script>
	export default {
		name: "CompanyReg",
		data() {
			var validatePass = (rule, value, callback) => {
				if (value !== this.airlineInfo.airlinePassword) {
					callback(new Error('两次输入密码不一致!'));
				} else {
					callback();
				}
			};
			return {
				airlineInfo: {
					airlineName: '',
					airlinePassword: '',
					airlinePassword2: '',
					airlineIdentity: '',
					airlinePhone: '',
					isvaild: '0' ,
				},
				rules: {
					airlineName: [{
							required: true,
							message: "公司名字不能为空",
							trigger: "blur"
						},
						{
							min: 2,
							max: 30,
							message: "长度在2-30之间",
							trigger: "blur"
						}
					],
					airlinePassword: [{
							required: true,
							message: "密码不能为空",
							trigger: "blur"
						},
						{
							min: 6,
							max: 20,
							message: "长度在6-20之间",
							trigger: "blur"
						}
					],
					airlinePassword2: [{
							required: true,
							message: "密码不能为空",
							trigger: "blur"
						},
						{
							min: 6,
							max: 20,
							message: "长度在6-20之间",
							trigger: "blur"
						},
						{
							validator: validatePass,
							trigger: "blur"
						}
					],
					airlineIdentity: [{
							required: true,
							message: "企业识别号不能为空",
							trigger: "blur"
						},
						{
							min: 15,
							max: 20,
							message: "长度在15-20之间",
							trigger: "blur"
						}
					],
					airlinePhone: [
						{
							required: true,
							message: "手机号不能为空",
							trigger: "blur"
						},
						{
							min: 6,
							max: 11,
							message: "请输入正确的手机号码或电话号码",
							trigger: "blur"
						}
					]
				}
			}
		},
		methods: {
			submitFrom(formName) {
      this.$refs[formName].validate(valid => {
        if (valid) {
          var that = this;
          this.$axios
            .post(
              "http://localhost:8087/register/airlineregister",
              that.airlineInfo
            )
            .then(res => {
              if (res.data == "注册成功") {
                //注册成功
                this.$message({
                  message: "注册成功,等待管理员审核",
                  type: "success"
                });
                //注册成功之后跳转到登录界面
                this.$router.push("/login");
              } else if (res.data == "用户名已存在") {
                //用户名已存在
                this.$message({
                  message: "公司名已存在",
                  type: "warning"
                });
                this.$refs[formName].resetFields();
              } else {
                //用户名已存在
                this.$message({
                  message: "注册失败",
                  type: "error"
                });
              }
            });
        } else {
          console.log("error submit!!");
          return false;
        }
      });
    },
    reback() {
      this.$router.push("/login");
    }
  }
}
</script>

<style scoped>
	.wapper {
		width: 450px;
		height: auto5;
		margin: 0 35% 30% 35%;
	}

	.avatar-uploader .el-upload {
		border: 1px dashed #5DADE2;
		border-radius: 6px;
		cursor: pointer;
		position: relative;
		overflow: hidden;
	}

	.avatar-uploader .el-upload:hover {
		border-color: #409EFF;
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
</style>
