var KTAB;
var TabUtil = {
    mid: '',
    url: '',
	setup: function() {
		let _this = this;
    	let _el = document.querySelectorAll('.k_menu_2 li a');
        [].forEach.call(_el, function(subEl, index) {
            subEl.onclick = function() {
				let n = document.querySelectorAll("#nav_" + subEl.dataset.mid);	
				if(n.length > 0) _this.selected(subEl.dataset.mid);
				else _this.create(subEl.dataset.mid,subEl.text,subEl.dataset.url);
            }
        });
    },
    create: function(mid,nm,url) {
		let _this = this;
		this.mid = mid;
		this.url = url;
		let n = document.querySelectorAll("#nav_" + mid);
		if(n.length > 0) return;
		
		this.initTab();
		let nav = document.querySelector(".k_nav_snb ul");
		let tab = document.createElement("li");
        tab.className =  mid+ " on";
        tab.id =  "nav_"+mid;
        tab.dataset.mid = mid;
		let a = document.createElement("a");
        a.href = "javascript:void(0);";
        a.text = nm;
        let b = document.createElement("button");
        b.className = "k_tab_del";
        b.textContent = "X";
        tab.append(a);
        tab.append(b);
        
        b.onclick = function() {
			_this.del(mid, this);
		};
        nav.append(tab);
        this.mkPage(mid,url);
        
        a.onclick = function() {
			_this.selected(mid);
		};
		this.changeMenu(mid);
		
		
    },
    del: function(mid, obj){
		let pObj = obj.parentElement.previousElementSibling;
		let nObj = obj.parentElement.nextElementSibling;
		
		obj.parentElement.remove();
		
		let p = document.getElementById("p_"+mid);
		p.remove();
		
		let len = document.querySelectorAll(".k_nav_snb ul li").length;
		if(len>0){
			let _obj = (pObj == null) ? nObj : pObj;
			
			this.selected(_obj.dataset.mid);
		}
	},
    mkPage: function(mid,url){
		let _this = this;
		
		this.initPage();
		
		let param = {};
		let t = {
            url: url
            , param: param
            , success: function(opt, data) {
				KTAB = _this;
				
		        let m = document.querySelector(".k_main");
				let p = document.createElement("div");
		        p.className = "k_page";
		        p.id =  "p_"+mid;
		        p.style.display = "block";
		        p.innerHTML = data;
		        //m.append(data);
		        console.log(m);
		        $(".k_main").append(p);
		        
				let a = document.createElement("div");
        		a.textContent = url;
        		a.className = "page_url";
        		a.style.color = "white";
        		p.prepend(a);
        		 
		        console.log(p);
				DOC = document.getElementById("p_"+mid);
		    	Page.setup();
		    	Page.bind();
		    	Page.load();
            }
        }
        AjaxUtil.get(t);
	},
    selected: function(mid) {
		KTAB = this;
		
		let n = document.getElementById("nav_"+mid);
		this.initTab();
		this.initPage();
		n.classList.add("on");
		n.style.display = "block";
		
		let b = n.querySelector("button");
		if(b != null)	b.style.display = "block";
        
		let p = document.getElementById("p_"+mid);
		p.style.display = "block";
		
		this.changeMenu(mid);
		
	},
	on: function(mid) {
		
	},
    initTab: function() {
    	let _el = document.querySelectorAll('.k_nav_snb ul li');
        [].forEach.call(_el, function(subEl, index) {
			let b = subEl.querySelector("button");
			if(b != null)	b.style.display = "none";
            subEl.classList.remove("on");
        });
        
        let m = document.querySelectorAll('.k_menu li');
        [].forEach.call(m, function(subEl, index) {
            subEl.classList.remove("on");
        });
        
	},
    initPage: function() {
    	let _el = document.querySelectorAll('.k_page');
        [].forEach.call(_el, function(subEl, index) {
			subEl.style.display = "none";
        });
	},
	changeMenu: function(mid){
		this.initMenu();
		this.mid = mid;
		let m = document.getElementById("m_"+this.mid);
		m.classList.add("on");
		DOC = document.getElementById("p_"+this.mid);
		
	},
    initMenu: function() {
    	let _el = document.querySelectorAll('.k_menu ul li');
        [].forEach.call(_el, function(subEl, index) {
            subEl.classList.remove("on");
        });
	}
};
