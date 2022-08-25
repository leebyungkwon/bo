let GRID = {
    id: this.id,
    url: this.url,
    init: true,
    search: "",
    orgData: "",
    opts: "",
    gridData: "",
    rowClick: false,
    sortNm: "",
    sort: "DESC",
    searchSeq: [],
    searchWidth: [],
    searchCol: [],
    searchDefault: [],
    isPaging: true,
    pageCnt: 0,
    page: 0,
    size: 10,
    params: {},
    gridSearch: "",
    validSearch:null,
    searchBtn: "",
    check: false,
    excel: "",
    bodyRefresh: false,
    initTable: true,
    pagingCnt: true,
    getId: function() {
        return this.id;
    },
    validation: function(obj) {
        if (obj.id == undefined) {
            alert("그리드 ID 입력하세요.");
            return;
        }
        if (obj.url == undefined) {
            alert("URL 입력하세요.");
            return;
        }
    },
    set: function(obj) {
        let _this = this;
        this.validation(obj);
        this.id = obj.id;
        this.url = obj.url;
        this.rowClick = obj.rowClick;
        this.search = (obj.search == undefined) ? "" : obj.search;
        this.sort = (obj.sort == undefined) ? "DESC" : obj.sort;
        this.sortNm = (obj.sortNm == undefined) ? "" : obj.sortNm;
        this.isPaging = (obj.isPaging == undefined) ? true : obj.isPaging;
        this.page = (obj.page == undefined) ? 0 : obj.page;
        this.size = (obj.size == undefined) ? 10 : obj.size;
        this.searchBtn = (obj.gridSearch == undefined) ? "" : obj.gridSearch.split(",")[1];
        this.gridSearch = (obj.gridSearch == undefined) ? "" : obj.gridSearch.split(",")[0];
        this.check = (obj.check == undefined) ? false : obj.check;
        this.excel = (obj.excel == undefined) ? false : obj.excel;
        this.initTable = (obj.initTable == undefined) ? true : obj.initTable;
        this.pagingCnt = (obj.pagingCnt == undefined) ? true : obj.pagingCnt;
        this.validSearch = (obj.validSearch == undefined) ? true : obj.validSearch;
        this.excelFileNm = (obj.excelFileNm == undefined) ? "엑셀다운로드" : obj.excelFileNm;
		
		
		//if(h_gridPage > 0)	this.page = h_gridPage;
		this.params = {
			'page': this.page
			, 'isPaging': this.isPaging
			, 'size': this.size
			, 'sort': this.sortNm + ',' + this.sort
			, 'excelFileNm' : this.excelFileNm
		};


		this.opts = obj;
		
		this.setTableBtn();
		
		if (this.initTable) this.getData();
		
		else this.returnData("");
		

        if (this.gridSearch != "") {
            if (this.gridSearch != "" && this.searchBtn != "") {
                document.getElementById(this.searchBtn).onclick = function() {
                    if (typeof _this.validSearch === "function"){
                      if(_this.validSearch())   _this.getData('init');
                    }else{
                      _this.getData('init');
                    }
                };
            }
        }
        
        
        let table = "";
        table += "<div style='width:100%;height: 30px;float: left;'>";
        table += "<div style='float:left;'>총 <span id='" + this.id + "_list_count'> </span>건</div>";
        table += "<div class='excelBx' id='"+this.id + "_excelBx'></div></div>";
		//document.getElementById(this.id).before(table);
		
		$("#"+this.id).before(table);
		
        if (this.excel != "") {
            let button = document.createElement("a");
            button.append("엑셀다운로드");
            button.className = "btn btn_home";
            console.log(document.getElementById(this.id + "_excelBx"));
            document.getElementById(this.id + "_excelBx").append(button);

            button.onclick = function() {
                let param = "";
                
				let params = {
					'page': _this.page
					, 'isPaging' : false
					, 'size' : _this.size
					, 'sort' : _this.sortNm+","+_this.sort
					, 'excelFileNm' : _this.excelFileNm
				}
				
                if (_this.gridSearch != "") {
					param = Object.assign({},params, WebUtil.getTagInParam(document.getElementById(_this.gridSearch)));
                }
                let p = {
                    url: _this.excel
                    , param: param
                }
                
                AjaxUtil.excel(p);
            };
        }

    },
    getData: function(type) {
        let _this = this;

        let param = "";
        
        console.log("1 === " , _this.pageCnt, _this.gridData.length, _this.params.page);
        if (this.gridSearch != "") {
            if (type != "undefined" && type == "init") {
                this.params.page = 0;
            }
        }
        param = Object.assign({}, this.params, WebUtil.getTagInParam(document.getElementById(this.gridSearch)));
        let p = {
            url: this.url
            , param: param
            , success: function(opt, result) {
                _this.returnData(result.data);
                
                console.log(_this.pageCnt, _this.gridData.length, _this.params.page);
                if(_this.pageCnt > 1 && _this.gridData.length == 0){
					if(_this.params.page > 0){
						_this.params.page = _this.params.page -1;
						_this.refresh();
						return false;
					}
				}
                
                if (typeof _this.opts.searchReturn === "function") {
                    _this.opts.searchReturn();
                }
            }
        }
        AjaxUtil.post(p);
    },
    returnData: function(data) {
        this.orgData = data;
        this.gridData = data;
        let page_id = this.id + "_paging";
        if (null != document.getElementById(page_id)) {
            document.getElementById(page_id).remove();
        }
        if (!this.bodyRefresh) this.setFrame();
        this.setTable();
        if (this.initTable || data.length > 0) {
            if (data.length > 0 && this.isPaging) this.setPaging(data[0].totalCnt);
        }


        if (this.pagingCnt) {
            let list_count = document.getElementById(this.id+"_list_count");

            let g = 0;
            if (data != 'undefined' && data.length > 0) g = data[0].totalCnt;
			
			list_count.innerHTML = g;
			/*	
            if (list_count_len == null) {
                let tot = document.createElement("span");
                tot.append("총 " + g + "건");
                tot.className = "list_count";
                tot.id = this.id + "_cnt";
                document.getElementById(this.id).before(tot);
            } else {
                if (document.getElementById(this.id + "_cnt") != null)
                    document.getElementById(this.id + "_cnt").innerHTML = "총 " + g + "건";
            }
            */
        }

        this.init = false;
        this.initTable = false;
    },
    setFrame: function() {
        let _this = this;
        let table = "";
        if (this.search != "") {
            if (this.search.searchUse == "Y") {
                for (i in this.opts.bodyCol) {
                    if (this.opts.bodyCol[i].search != undefined) {
                        let typ = this.opts.bodyCol[i].search["type"];
                        if (typ != undefined) {
                            this.searchCol.push(this.opts.bodyCol[i]);
                            this.searchSeq.push(i);
                            this.searchDefault.push(this.opts.bodyCol[i]["search"]["default"]);
                            this.searchWidth.push(this.opts.bodyCol[i]["search"]["width"]);
                        }
                    }
                }
                table += "<table class='searchTable' id='tbl_" + this.id + "_search' style='width:" + this.opts.width + "'>" + "<tbody></tbody></table>";
            }
        }

        table += "<table class='gridTableHead' id='tbl_" + this.id + "_head' style='width:" + this.opts.width + "'>"
            + "<thead></thead></table>";


        table += "<table class='gridTableBody' id='tbl_" + this.id + "_body' style='width:" + this.opts.width + "'>"
            + "<tbody></tbody></table>";
        document.getElementById(this.id).innerHTML = table;
    },
    setTable: function() {
        let hiddenCol = this.columnSetHiddenCol(this.opts.bodyCol);

        if (!this.bodyRefresh) {
            this.setHead("tbl_" + this.id + "_head", this.opts.headCol, hiddenCol, this.opts.bodyCol);
            this.bodyRefresh = true;
        }

        this.setBody("tbl_" + this.id + "_body", this.opts.bodyCol, hiddenCol);

        if (this.init && this.search != "") {
            if (this.search.searchUse == "Y") {
                document.getElementById(this.id + "_search").onclick = function() { _this.data(); };
            }
        }

    },
    setData: function() {
        this.gridData = [];
        let searchName = [];
        let searchWay = [];
        if (this.search != "") {
            if (this.search.searchUse == "Y") {
                for (i in this.searchCol) {
                    searchName.push(this.searchCol[i]["name"]);
                    searchWay.push(this.searchCol[i]["search"]["way"]);
                }
            }
        }
        let data = this.orgData;

        let emp = true;
        searchName.forEach(function(v, i) {
            let val = document.getElementById("tbl_" + _this.id + "_search_" + v).value;
            if (val != "전체") {
                emp = false;
                return;
            }
        });
        if (emp) {
            this.gridData = data;
        } else {
            this.gridData = data.filter(function(item) {
                let d = true;
                searchName.forEach(function(v, i) {
                    if (!d) return;
                    if (document.getElementById("tbl_" + _this.id + "_search_" + v).value != "전체") {
                        let val = document.getElementById("tbl_" + _this.id + "_search_" + v).value;
                        d = (item[v] === document.getElementById("tbl_" + _this.id + "_search_" + v).value);
                    }
                });
                return d;
            });
        }

    },
    setPaging: function(totalCnt) {
        let _this = this;
        let page_id = this.id + "_paging";
        let current = parseInt(_this.params.page) + 1;
        this.pageCnt = Math.ceil(totalCnt / this.size);

        let pagingBtnArea = document.createElement("div");
        pagingBtnArea.className = "pagination_btn_area mt10";
        pagingBtnArea.id = page_id + "_area";
		
		let pn_select = "";
		let pn = '<select id="'+page_id+'_size" style="float:left;">'
		+'<option value="10" selected="selected">10개</option>'
		+'<option value="30">30개</option>'
		+'<option value="50">50개</option>'
		+'</select>';
		
		
        let tag = document.createElement("div");
        tag.className = "custom-pagination center";
        tag.id = page_id;
        let s_tag = "";
        let start = (parseInt((current - 1) / 10) * 10) + 1;
        let end = Math.floor(start+9);
        end = (this.pageCnt > end) ? end : this.pageCnt;
        
        s_tag = pn;

        if (this.pageCnt > 1) {
            if (1 < current) {
				s_tag += '<span  class="pagination-first"><a href="javascript:void(0)" class=""><<</a></span>';
                s_tag += '<span  class="pagination-prev"><a href="javascript:void(0)" class=""><</a></span>';
            }
            for (let i = start; i <= end; i++) {
                s_tag += '<span class="num num' + i + '"><a href="javascript:void(0)" class="page_num">' + i + '</a></span>';
            }
            if (this.pageCnt > current) {
                s_tag += '<span  class="pagination-next"><a href="javascript:void(0)" class="">></a></span>';
                s_tag += '<span  class="pagination-last"><a href="javascript:void(0)" class="">>></a></span>';
            }
        } else {
            s_tag += '<span class="num num1"><a href="javascript:void(0)" class="page_num">1</a></span>';
        }
        tag.innerHTML = s_tag;

        if (document.getElementById(page_id + "_area") != null) {
            document.getElementById(page_id + "_area").remove();
        }
        pagingBtnArea.appendChild(tag);
        document.getElementById(this.id).appendChild(pagingBtnArea);


        let page = document.getElementById(page_id).querySelectorAll(".page_num");
        let prev = document.getElementById(page_id).querySelector(".pagination-prev");
        let next = document.getElementById(page_id).querySelector(".pagination-next");

        if (null != prev) {
            prev.addEventListener('click', function(event) {
                for (let __page = 0; __page < page; __page++) {
                    __page.parentElement.classList.remove("current");
                }
                _this.params.page = _this.params.page - 1;
                _this.getData();
            });
        }
        if (null != next) {
            next.addEventListener('click', function(event) {
                for (let __page = 0; __page < page; __page++) {
                    __page.parentElement.classList.remove("current");
                }
                _this.params.page = _this.params.page + 1;
                _this.getData();
            });
        }

        let idx = 1;

        for (let _page = 0; _page < page.length; _page++) {
            page[_page].addEventListener('click', function(event) {
                for (let __page = 0; __page < page.length; __page++) {
                    page[__page].parentElement.classList.remove("current");
                }
                _this.params.page = this.text - 1;
                _this.getData();
            });
        }
        /*
        let pageSizeChange = document.getElementById(page_id + '_size');
        for(i=0; i <pageSizeChange.option.length;i++){
			if(pageSizeChange.option[i].value == this.size){
				pageSizeChange.optoin[i].selected = true;
				break;
			}
		}
		
		pageSizeChange.addEventListener('change', function(event) {
			_this.size = this.value;
			_this.params.size = this.value;
			_this.params.page = 0;
			_this.refresh();
		});
		*/
        if (document.getElementById(page_id).querySelector(".num" + (parseInt(_this.params.page) + 1)) != null)
            document.getElementById(page_id).querySelector(".num" + (parseInt(_this.params.page) + 1)).className = "num current num" + _this.params.page + 1;
    },
    setSearch: function(id, headCol, data) {
        let tag = "<tr>";
        let colspan = 0;
        for (i in headCol) {
            for (j in this.searchSeq) {
                if (i == j) {
                    colspan++;
                    tag += "<td class='searchTableTop'>";
                    tag += "<label>" + headCol[this.searchSeq[j]] + "</label><span>";

                    let selecteName = (this.searchDefault[j] != undefined) ? this.searchDefault[j] : "";
                    if (this.searchCol[j].search["type"] == "text") {
                        tag += "<input type='text' name='" + id + "_" + this.searchCol[j].name + "' id='" + id + "_" + this.searchCol[j].name + "' style='margin-left:10px;width:80px;'/>";
                    }
                    else if (this.searchCol[j].search["type"] == "select") {
                        tag += "<select name='" + id + "_" + this.searchCol[j].name + "' id='" + id + "_" + this.searchCol[j].name + "' style='margin-left:10px;width:" + this.searchWidth[j] + ";'>";
                        let searchName = this.searchCol[j].name;
                        let _name = [];
                        let prev_name = "";
                        tag += "<option>전체</option>";
                        for (k in data) {
                            if (prev_name != data[k][searchName]) {
                                prev_name = data[k][searchName];
                                let _name_flag = true;
                                for (m in _name) {
                                    if (_name[m] == prev_name) {
                                        _name_flag = false;
                                        continue;
                                    }
                                }
                                if (_name_flag) _name.push(prev_name);
                            }
                        }
                        for (k in _name) {
                            let selected = (selecteName == _name[k]) ? "selected" : "";
                            tag += "<option value='" + _name[k] + "' " + selected + ">" + _name[k] + "</option>";
                        }
                        tag += "</select>";
                    }

                    tag += "</span></td>";
                }
            }
        }

        tag += "</tr>";
        tag += "<tr class='searchTableSearch'><td id='" + this.id + "_search' colspan='" + colspan + "'>검  색</td></tr>"

        document.getElementById(id).getElementsByTagName("tbody")[0].innerHTML = tag;

    },
    setHead: function(id, headCol, hiddenCol, bodyCol) {
        let _this = this;
        let tag = "<tr>";
        let hiddenStr = "display:none;";
        let cursorStr = "";
        if (this.opts.sortTable)    cursorStr = "cursor:pointer;";
        if (this.check) tag += "<th style='text-align:center;width:1px;padding-right:0px;'><input type='checkbox' class='commonCheckBox' style='display:block'/></th>";
        for (i in headCol) {
            tag += "<th style='text-align:center; width:" + bodyCol[i].width + "; " + cursorStr;
            for (j in hiddenCol) if (i == hiddenCol[j]) tag += hiddenStr;
            tag += "'";
            tag += " data-col=" + WebUtil.trim(WebUtil.camel2Snake(this.opts.bodyCol[i].name, "")) + "";
            tag += " data-sort='DESC'";
            tag += ">";
            tag += headCol[i] + "</th>";
        }
        tag += "</tr>";
        let thead = document.getElementById(id).getElementsByTagName("thead")[0];
        thead.innerHTML = tag;
        if (this.opts.sortTable) {
            for (i in headCol) {
                if (this.check && i == 0) continue;
                thead.querySelectorAll("th")[i].onclick = function() {

                    document.getElementById(id).querySelectorAll(".grid_head").forEach(function(item) {
                        item.className = '';
                    });
                    if (WebUtil.isNull(this.dataset.sort)) {
                        this.dataset.sort = 'ASC';
                        this.className = 'grid_head grid_asc';
                    } else if (!WebUtil.isNull(this.dataset.sort) && this.dataset.sort == 'ASC') {
                        this.dataset.sort = 'DESC';
                        this.className = 'grid_head grid_desc';
                    }
                    else if (!WebUtil.isNull(this.dataset.sort) && this.dataset.sort == 'DESC') {
                        this.dataset.sort = 'ASC';
                        this.className = 'grid_head grid_asc';
                    }
                    _this.bodyRefresh = true;
                    _this.params.sort = this.dataset.col + "," + this.dataset.sort;
                    _this.params.page = 0;
                    _this.getData();
                }

            }
        }

        if (this.check) {
            thead.getElementsByTagName("input")[0].onclick = function() {
                let headCheck = this.checked;
                if (this.type == 'checkbox') {
                    let chkBody = document.getElementById("tbl_" + _this.id + "_body").getElementsByTagName("tbody")[0].getElementsByTagName("input");
                    [].forEach.call(chkBody, function(el, idx) {
                        if (headCheck) el.checked = true;
                        else el.checked = false;
                    });
                }
            };
        }
    },
    setBody: function(id, bodyCol, hiddenCol) {
        let _this = this;
        //this.setData();

        let tag = "";
        let hiddenStr = "display:none;";

        if (this.gridData == 0) {
            if(WebUtil.isNotNull(this.opts.noDataMsg))tag += "<tr><td colspan='" + bodyCol.length + "' style='text-align:center;font-weight:bold;'>"+this.opts.noDataMsg+"</td></tr>";
            else    tag += "<tr><td colspan='" + bodyCol.length + "' style='text-align:center;font-weight:bold;'>데이터가 없습니다.</td></tr>";

            document.getElementById(id).getElementsByTagName("tbody")[0].innerHTML = tag;
        } else {
            for (i in this.gridData) {
                tag += "<tr>";
                if (this.check) tag += "<td class='noClick' style='text-align:center;width:1px;padding-right:0px;'><input type='checkbox' style='display:block'/></td>";
                for (j in bodyCol) {
                    let typ = bodyCol[j].type;
                    let name = bodyCol[j].name;
                    let dataStr = (this.gridData[i][name] == undefined) ? "" : this.gridData[i][name];

                    //if (typeof dataStr == 'string') dataStr = WebUtil.XSSCheck(dataStr,1);
                    if (bodyCol[j].custom != undefined) dataStr = "";
                    let classNm = "tbodyTd";
                    if (bodyCol[j].button != undefined || bodyCol[j].custom != undefined) classNm = "noClick";
                    if (typ == "text") classNm = "noClick";
                    tag += "<td class='" + classNm + "' style='";

                    for (k in hiddenCol) if (hiddenCol[k] == j) tag += hiddenStr;

                    let align = (bodyCol[j].align == undefined) ? "center" : bodyCol[j].align;
                    tag += "text-align:" + align + ";" + "width:" + bodyCol[j].width + ";'>";
                    /*tag += (typ == "text") ? "<input type='text' name='" + name + "' value='" + dataStr + "'/>" : dataStr;*/
                    tag += dataStr;
                    if (bodyCol[j].button != undefined) {
                        for (key in bodyCol[j].button) {
                            tag += "<a href='javascript:void(0)' class='btn_04 border w80' onclick=" + bodyCol[j].button[key] + "(" + i + ");>" + key + "</a>";
                        }
                    } else if (bodyCol[j].custom != undefined) {
                        tag += bodyCol[j].custom(this.gridData[i]);
                    }
                    tag += "</td>";

                }
                tag += "</tr>";
            }
            document.getElementById(id).getElementsByTagName("tbody")[0].innerHTML = tag;
            if (_this.rowClick) {
                let tbodyTr = document.getElementById(id).getElementsByTagName("tbody")[0].getElementsByTagName("tr");
                //let tbodyTr = document.getElementById(id).getElementsByTagName("tbody")[0].querySelectorAll('td:not([class*="tbodyCheck"])');
                [].forEach.call(tbodyTr, function(el, idx) {
                    let _el = el.querySelectorAll('td:not([class*="noClick"])');
                    el.style.cursor = "pointer";
                    [].forEach.call(_el, function(subEl, index) {
                        subEl.onclick = function() {
                            _this.clickRemove();
                            subEl.parentElement.style.background = _this.rowClick.color;
                            subEl.parentElement.className = _this.id + "_selected";
                            subEl.parentElement.dataset.row = idx;
                            _this.setHistory();
                            if (_this.rowClick.retFunc != undefined) _this.rowClick.retFunc(idx, _this.gridData[idx]);
                        }
                    });

                });
            }
        }

    },
    setTableBtn: function() {
        let _this = this;
        if (this.opts.tableBtn != undefined) {
            let pagination_btn_area = document.getElementById("btnDiv");
            let i = 0;
            for (key in this.opts.tableBtn.button) {
                let right = document.createElement("a");
                right.className = "btn_07 border mr12";
                right.id = "tableBtn" + i++;
                right.text = key;
                pagination_btn_area.append(right);
            }
            i = 0;

            let page_id = this.id + "_paging_area";

            //document.getElementById("btnDiv").append(button);


            for (key in this.opts.tableBtn.button) {
                pagination_btn_area.querySelector("#tableBtn" + i++).onclick = function() {
                    if (_this.opts.tableBtn.button[key].retFunc != undefined) {
                        let body = _this.opts.bodyCol;
                        let id = "";
                        let r = new Array();
                        for (j in body) {
                            if (body[j].id) id = body[j].name;
                        }
                        for (j in body) {
                            if (body[j].type == "text") {
                                let dt = document.getElementsByName(body[j].name);
                                for (let k = 0; k < dt.length; k++) {
                                    let rd = {};
                                    rd[id] = _this.gridData[k][id];
                                    rd[body[j].name] = dt[k].value;
                                    r.push(rd);
                                }
                            }
                        }
                        _this.opts.tableBtn.button[key].retFunc(r);
                    }
                }
            }
        }

    },
    columnSetHiddenCol: function(bodyCol) {
        let hiddenCol = [];
        for (i in bodyCol) {
            let hidden = bodyCol[i].hidden;
            if (hidden) hiddenCol.push(i);
        }
        return hiddenCol;
    },
    clearBody: function() {
        document.getElementById("tbl_" + this.id + "_body").getElementsByTagName("tbody")[0].innerHTML = "";
    },
    refresh: function() {
        this.getData();
    },
    clickRemove: function() {
        let _tr = document.getElementById(this.id).getElementsByTagName("tbody")[0].getElementsByTagName("tr");
        for (let i = 0; i < _tr.length; i++) {
            _tr[i].className = "";
            if (typeof _tr[i].style.background != undefined) _tr[i].style.background = "";
        }
    },
    getChkData: function() {
        let _this = this;
        let chkBody = document.getElementById("tbl_" + this.id + "_body").getElementsByTagName("tbody")[0].getElementsByTagName("input");
        let i = 0;
        let data = {};
        [].forEach.call(chkBody, function(el, idx) {
            if (el.checked) data[i++] = _this.gridData[idx];
        });
        return data;
    },
    getRowData: function() {
        let row = (document.getElementById("tbl_" + this.id + "_body").querySelector("." + this.id + "_selected") == null) ? "" : document.getElementById("tbl_" + this.id + "_body").querySelector("." + this.id + "_selected").dataset.row;
        return (row == "") ? "" : this.gridData[row];
    },
    getGridInputData: function() {
        let _this = this;
        let body = _this.opts.bodyCol;
        let id = "";
        let r = new Array();
        for (j in body) {
            if (body[j].id) id = body[j].name;
        }

        let tag = $("#" + "tbl_" + this.id + "_body tr");

        var resultData = new Array();
        tag.each(function(idx) {
            let _tag = this;
            var result = {};
            result[id] = _this.gridData[idx][id];
            $(_tag).find('input[name] , select[name], textarea').filter(function(index, selector) {
                var selectorTag = $(selector).prop('tagName');
                var selectorName = $(selector).prop('name');

                if (WebUtil.isNotNull(selectorName)) {
                    var selectorType = $(selector).prop('type');

                    var value = '';
                    var checkData = new Array();
                    if (selectorType == 'radio') result[selectorName] = $(_tag).find('input[name="' + selectorName + '"]:checked').val();
                    if (selectorTag == 'SELECT') result[selectorName] = $(_tag).find('select[name="' + selectorName + '"]').val();
                    if (selectorType == 'textarea') result[selectorName] = $(_tag).find('textarea[name="' + selectorName + '"]').val();
                    if (selectorType == 'text') result[selectorName] = $(_tag).find('input[name=' + selectorName + ']').val();
                    if (selectorType == 'hidden') result[selectorName] = $(_tag).find('input[name=' + selectorName + ']').val();
                }
            });
            resultData.push(result);

        });

        return resultData;
    },
    setHistory: function(){
		let _this = this;
		sessionStorage.setItem('P_'+window.location.pathname, JSON.stringify(document.getElementById(_this.gridSearch)));
		sessionStorage.setItem('PAGE_'+window.location.pathname, _this.params.page);
	}
    
};
