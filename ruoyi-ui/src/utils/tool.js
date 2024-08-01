export default function(obj){
  let flag=false;
  if ((typeof obj === 'string')){
      flag=(!obj || obj.replace(/\s+/g, "") === "");
  }

  else{
    flag=(!obj || JSON.stringify(obj) === "{}" || obj.length === 0);
  }

  if(flag==false){
    //不为空 进行复制完成
    return JSON.parse(JSON.stringify(obj));
  }
}
