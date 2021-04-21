<template>
<div>
  <div>
    <el-form :rules="rules" ref="announcementInfo" :model="announcementInfo" label-width="80px">
      <el-form-item label="标题" prop="titile" style="width:500px">
        <el-input v-model="announcementInfo.titile"></el-input>
      </el-form-item>
      <el-form-item label="详情" prop="discribe">
        <el-input type="textarea" v-model="announcementInfo.discribe"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" @click="submitForm('announcementInfo')">立即创建</el-button>
        <el-button @click="resetForm('announcementInfo')">重置</el-button>
      </el-form-item>
    </el-form>
  </div>
	<!-- 已有公告列表 -->
	<div style="margin-top:20px">

	</div>
	</div>
</template>

<script>
export default {
  name: "Announcement",
  data() {
    return {
			announcementInfo: {
				titile: '',
				discribe: '',
			},
			rules: {
				titile: [
					{ required: true, message: '请输入标题', trigger: 'blur' }
				],
				discribe: [
					{ required: true, message: '请输入内容', trigger: 'blur' }
				]
			}
		};
  },
  created() {},
  methods: {
		submitForm(formName) {
        this.$refs[formName].validate((valid) => {
          if (valid) {
						// this.$axios.post("http://localhost:8087/announcement/add",this.announcementInfo)
						//   .then(res => {
						// 		console.log(res);
						// 	})
            console.log(this.announcementInfo);
          } else {
            console.log('error submit!!');
            return false;
          }
        });
      },
      resetForm(formName) {
        this.$refs[formName].resetFields();
      }

	}
};
</script>

<style>
</style>
