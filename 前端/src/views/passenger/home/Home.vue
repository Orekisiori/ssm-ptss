<template>
  <el-container>
    <el-header>
      <Header></Header>
      <div class="middle">
        <div>
          <RightBer></RightBer>
        </div>
        <div>
          <LeftBer></LeftBer>
        </div>
      </div>
      <Show></Show>
      <Footer></Footer>
    </el-header>
  </el-container>
</template>

<script>
  import Header from './Header'
  import RightBer from './Rightber'
  import LeftBer from './Leftber'
  import Show from './Show'
  import Footer from './Footer'
  export default {
    name: 'Home',
    components: {
      Header,
      RightBer,
      LeftBer,
      Show,
      Footer
    }
  }
</script>

<style scoped>
  body {
    padding: 0;
    margin: 0;
  }

  .middle {
    padding: 0;
    margin: 0 0 0 0;
    width: 100%;
    height: 400px;
  }
</style>
