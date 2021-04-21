<template>
  <div class="rightber">
    <div class="contanter">
      <el-carousel :interval="2400" type="card" height="365px">
        <el-carousel-item v-for="item in 4" :key="item">
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
.el-carousel__item:nth-child(3) {
  background-image: url("../../../assets/img/Tower.jpg");
  width: 100%;
  height: 100%;
}

.el-carousel__item:nth-child(4) {
  background-image: url("../../../assets/img/Aurora.jpg");
  width: 100%;
  height: 100%;
}
.el-carousel__item:nth-child(5) {
  background-image: url("../../../assets/img/Beach.jpg");
  width: 100%;
  height: 100%;
}
.el-carousel__item:nth-child(6) {
  background-image: url("../../../assets/img/Paris.jpg");
  width: 100%;
  height: 100%;
}

.el-carousel__item h3 {
  color: #475669;
  font-size: 14px;
  opacity: 0.75;
  line-height: 200px;
  margin: 0;
}

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
</style>
