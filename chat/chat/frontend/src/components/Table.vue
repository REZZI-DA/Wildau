<template>
  <div class="container text-center mt-5 mb-5">

      <table class="table table-bordered table-striped">
        <thead>
          <tr>
            <th>ID</th>
            <th>Username</th>
            <th>Password</th>
            <th>Email</th>
            <th>Phone Number</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="(user, index) in userData" :key="index" :class="{ 'table-row-even': index % 2 === 0 }">
            <td>{{ user.id }} <i class="bi bi-sort-alpha-down" aria-label="Sort Icon"></i></td>
            <td>{{ user.username }} <i class="bi bi-sort-alpha-down" aria-label="Sort Icon"></i></td>
            <td>{{ user.password }} <i class="bi bi-sort-alpha-down" aria-label="Sort Icon"></i></td>
            <td>{{ user.email }} <i class="bi bi-sort-alpha-down" aria-label="Sort Icon"></i></td>
            <td>{{ user.phoneNumber }} <i class="bi bi-sort-alpha-down" aria-label="Sort Icon"></i></td>
          
          </tr>
        </tbody>
      </table>
  </div>
</template>
<script>

export default {
  name: 'TableComponent',
  props:{
      // 
      userData:{
          type: Array,
      }
    
  }, 
}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
h3 {
  margin: 40px 0 0;
}

ul {
  list-style-type: none;
  padding: 0;
}

li {
  display: inline-block;
  margin: 0 10px;
}

a {
  color: #42b983;
}</style>
