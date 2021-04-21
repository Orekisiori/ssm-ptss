<template>
  <div class="rightber">
    <div class="contanter">
      <el-carousel :interval="1000" type="card" height="365px">
        <el-carousel-item v-for="item in 2" :key="item">
          <h3 class="medium">{{item }}</h3>
        </el-carousel-item>
      </el-carousel>
    </div>
  </div>
</template>

<script>
export default {
  name: "RighrBer"
};
</script>

<style>
.rightber {
  padding: 0;
  margin: 0 0 0 0;
  width: 50%;
  height: 400px;
  background-color: #42b983;
  align-items: center;
  float: right;
}
.contanter {
  padding: 2px 2px 2px 2px;
  background-color: #5f9ea0;
  align-items: center;
  justify-content: center;
}

.el-carousel__item h3 {
  color: #475669;
  font-size: 14px;
  opacity: 0.75;
  line-height: 200px;
  margin: 0;
}

.el-carousel__item:nth-child(2n) {
  background-image: url("../../assets/img/2.jpg");
  widows: 100%;
  height: 100%;
}

.el-carousel__item:nth-child(2n + 1) {
  background-image: url("../../assets/img/3.jpg");
  widows: 100%;
  height: 100%;
}
</style>
