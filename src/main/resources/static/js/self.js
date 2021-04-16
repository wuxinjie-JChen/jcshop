//页头
Vue.component(
	'yetou',
	{
		data : function(){
			return { user:{} };
		},
		created(){
			axios.post("user/checkLogin").then(jc=>{
				if(jc.data !== ""){
					this.user=jc.data;
				}
			});
		},
		methods:{
			logout(){
				axios.post("user/logout").then(jc=>{
					location.reload();
				});
			}
		},
		template : `<div class="soubg">
	<div class="sou">
        <span class="fr">
        	<span class="fl">
        		<span v-if="user.name">
        			<a href="#">{{user.name}}</a>&nbsp;
        			<a href="#" @click.prevent="logout()">退出登录</a>&nbsp;
        		</span>
        		<a v-else href="login.html">您好，请登录</a>&nbsp; 
        		<a href="regist.html" style="color:#ff4e00;">免费注册</a>&nbsp;|&nbsp;<a href="Member_Order.html">我的订单</a>&nbsp;|</span>
        	<span class="ss">
            	<div class="ss_list">
                	<a href="#">收藏夹</a>
                    <div class="ss_list_bg">
                    	<div class="s_city_t"></div>
                        <div class="ss_list_c">
                        	<ul>
                            	<li><a href="#">我的收藏夹</a></li>
                                <li><a href="#">我的收藏夹</a></li>
                            </ul>
                        </div>
                    </div>     
                </div>
                <div class="ss_list">
                	<a href="#">客户服务</a>
                    <div class="ss_list_bg">
                    	<div class="s_city_t"></div>
                        <div class="ss_list_c">
                        	<ul>
                            	<li><a href="#">客户服务</a></li>
                                <li><a href="#">客户服务</a></li>
                                <li><a href="#">客户服务</a></li>
                            </ul>
                        </div>
                    </div>    
                </div>
                <div class="ss_list">
                	<a href="#">网站导航</a>
                    <div class="ss_list_bg">
                    	<div class="s_city_t"></div>
                        <div class="ss_list_c">
                        	<ul>
                            	<li><a href="index.html">首页</a></li>
                                <li><a href="#">网站导航</a></li>
                            </ul>
                        </div>
                    </div>    
                </div>
            </span>
            <span class="fl">|&nbsp;关注我们：</span>
            <span class="s_sh"><a href="#" class="sh1">新浪</a><a href="#" class="sh2">微信</a></span>
            <span class="fr">|&nbsp;<a href="#">手机版&nbsp;<img src="images/s_tel.png" align="absmiddle" /></a></span>
        </span>
    </div>
</div>`
	}
)


//购物车
Vue.component(
	'cart',
	{
		data : function(){
			return { list:[],
				isLogin:false };
		},
		created(){
			axios.post("cart/findCart").then(res=>{
				this.list=res.data;
			});
			axios.post("user/checkLogin").then(jc=>{
				if(jc.data !== ""){
					this.isLogin=true;
				}
			});
		},
		methods:{
			detail(id){
				location.href="product.html#"+id;
				location.reload();
			}
		},
		computed:{
			total(){
				var total=0;
				if(this.list.length==0){
					return 0;
				}else{
					for(c of this.list){
    					total+=c.good.price*c.num;
    				};
    				return total;
				}
				
			}
		},
		template : `<div class="i_car">
    	<div class="car_t">购物车 [ <span>3</span> ]</div>
        <div class="car_bg">
       		<!--Begin 购物车未登录 Begin-->
        	<div v-if="!isLogin" class="un_login">还未登录！<a href="login.html" style="color:#ff4e00;">马上登录</a> 查看购物车！</div>
            <!--End 购物车未登录 End-->
            <!--Begin 购物车已登录 Begin-->
            <div v-else>
            	<ul class="cars">
	            	<li v-for="l in list">
	                	<div class="img"><img :src="l.good.img" width="58" height="58" /></div>
	                    <div class="name"><a href="#" @click.prevent="detail(l.good.id)">{{l.good.name}}</a></div>
	                    <div class="price"><font color="#ff4e00">￥{{l.good.price}}</font> <font style="float:right">数量：{{l.num}}</font></div>
	                </li>
	            </ul>
	            <div class="price_sum">共计&nbsp; <font color="#ff4e00">￥</font><span>{{total}}</span></div>
	            <div class="price_a"><a href="BuyCar.html">去购物车结算</a></div>
            </div>
            <!--End 购物车已登录 End-->
        </div>
    </div>`
	}
)


//全部分类
Vue.component(
	'category',
	{
		data : function(){
			return { names:[],
				categorys:[] };
		},
		created(){
			axios.post("category/find").then(jc=>{
				this.categorys=jc.data.categorys;
				this.names=jc.data.names;
			});
		},
		template : `<div id="category">
	<div class="menu">
    	<!--Begin 商品分类详情 Begin-->    
    	<div class="nav">
        	<div class="nav_t">全部商品分类</div>
            <div class="leftNav">
                <ul>      
                    <li v-for="n in names">
                    	<div class="fj">
                        	<span class="n_img"><span></span><img src="images/nav1.png" /></span>
                            <span class="fl">
                            	<a :href="'index.html#'+n">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;{{n}}</a>
                            </span>
                        </div>
                    </li>
                </ul>            
            </div>
        </div>  
        <!--End 商品分类详情 End-->                                                     
    	<ul class="menu_r">                                                                                                                                               
        	<li><a href="Index.html">首页</a></li>
            <li v-for="n in names"><a :href="'index.html#'+n">{{n}}</a></li>
        </ul>
        <!-- <div class="m_ad">中秋送好礼！</div> -->
    </div>
</div>`
	}
)