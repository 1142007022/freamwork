function move(){
        //��ȡ�ƶ���DIV����
        var obj = document.getElementById("container");
        //������ʽ��λ���ԣ���div�ӵ�ǰ�ĵ������ϳ���
        //��������������������塣���Բ����
        obj.style.position = "absolute";
        obj.style.opacity = 0;
        //������������
        var num = 0;
        //����ƶ�div��������������X����
        var left = 630;
        //����ƶ�div��������������Y����
        var top = 220;
        //ʹ�ö�ʱ���ƶ�DIV
        var timer  =  setInterval(function(){ //�ƶ��ĺ���
          if(num==105){ //�ƶ�105��
            clearInterval(timer); 
          }
          //ͨ��left��ʽ�������á��������λ
          obj.style.left = left + "px";
          //ͨ��top��ʽ�������ã��������λ
          obj.style.top = top - num * 2 + "px";
          //��������һ
          obj.style.opacity =   num  / 140.0;
          num++;
        },190);
}