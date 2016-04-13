<%@ page contentType="text/html; charset=utf-8" language="java"
	import="java.sql.*" errorPage=""%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%
String reviewList = (String)request.getSession().getAttribute("reviewList");
if(reviewList==null || reviewList.equals("")) {
	response.sendRedirect("../listReviews.do");
}
reviewList = reviewList.substring(1);
%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Interaction</title>
<script src="../jquery/jquery-1.9.1.min.js"></script>
<script src="../jquery/menu.js"></script>
<script src="../js/stringUtil.js"></script>
<link href="../css/interaction_style.css" rel="stylesheet" type="text/css" />
<link href="../css/interaction.css" rel="stylesheet" type="text/css" />
</head>
<body>
	<div id="con">
		<div id="menu">
			<center>
				<a id="nav0" class="nav_list" href="about.html"> <span>About
						Us</span></a> <a id="nav1" class="nav_list" href="portfolio.jsp"> <span>Portfolio</span></a>
				<a id="nav2" class="nav_list" href="services.html"> <span>Services</span></a>
				<a id="nav3" class="nav_list" href="specialties.jsp"> <span>Specialties</span></a>
				<a id="nav4" class="Snav_list" href="interaction.jsp"> <span>Interaction
						Center</span></a> <a id="nav5" class="nav_list" href="contact.html"> <span>Contact
						Us</span></a>
			</center>
		</div>
		<div id="right">
			<div>
				<font size="+4">Interaction Center</font>
			</div>
			<!-- 
      	这里是显示的东西
   -->
			<div id='main'>
				<div id='faq'>
					<p class='title'>FAQ</p>
					<div class='faq_list'>
					<span>Q</span> <br />
					<span>How much does the Right to Buy process cost me from Day 1 until completion? </span><br />
					<span>A</span> <br />
					<span>Nothing!! Taylor Made Solutions - building with a difference </span> <br />
					<span>Q</span> <br />
					<span>How long does the build take and do I have to move out? </span><br />
					<span>A</span> <br />
					<span>The build takes 4-6 weeks depending on house type, ie Cornish type - 4 weeks, Airey or Wates - 6 weeks. </span> <br />
					</div>
					<div class='read_more'>
						<a href='#'>read more></a>
					</div>
				</div>
				<div class='verticalLine'></div>
				<div id='mfu'>
					<p class='title'>Message For Us</p>
					<div class='message_us'>
						<form action='../uploadMessage.do'>
							<div>
								<span>Name </span> <input class='input' name='name' placeholder='你的名字，最多10字' title='你的名字，最多10字' type='text' maxlength='10' required />
							</div>
							<div>
								<span>Phone </span> <input class='input' name='phone' type='text' autocomplete='off' 
								pattern='^1[345678][0-9]{9}$' required title='应当是一个手机号码' placeholder='应当是一个手机号码' />
							</div>
							<div>
								<span>Your Message </span>
								<textarea id='message' name='message' maxlength='100' title='你的信息，最多100字' placeholder='你的信息，最多100字' rows='12' cols='43' style='resize: none;' required></textarea>
								<span id='remainNum'>还剩 100 字</span>
							</div>
							<div>
								<input class='submit' type='submit' value='提交' />
							</div>
						</form>
					</div>
				</div>
				<div class='verticalLine'></div>
				<div id='reviews'>
					<p class='title'>Reviews</p>
					<div class='reviews_list'>
					<%
					String[] ret = reviewList.split(String.valueOf((char)4));
					for(String r : ret) {
						String[] infor = r.split(String.valueOf((char)3));
						out.print("<span>名字："+infor[0]+"</span>&nbsp;");
						out.print("<span>电话："+infor[1]+"</span><br />");
						out.println("<span>"+infor[2]+"</span><br />");
					}
					%>
					</div>
					<div class='read_more'>
						<a href='#'>read more></a>
					</div>
				</div>
			</div>
		</div>
	</div>
	<a href="#" id="ar"></a>
	<div id="tag">
		<hr />
		<a href="../index.html" style="position: absolute; left: 0px;">Home></a>
		<a href="contact.html" style="position: absolute; left: 320px;">Contact
			Us></a> <br /> Shanghai Xiao Yu House Repair Ltd.Co.
	</div>
	<script type='text/javascript'>
	pos = 0;
	$(document).ready(function() {
		document.body.style.height = window.innerHeight+'px';
		$('#ar')[0].style.left = '49%';
		$('#tag')[0].style.left = '29%';
		$("#ar").fadeIn(2500);
		$("#tag").fadeIn(2500);

		$('#ar')[0].addEventListener('click', function() {
			var menuWidth = parseInt(getComputedStyle($('#menu')[0]).width);
			var mainWidth = parseInt(getComputedStyle($('#main')[0]).width);
			if(pos == 0) {
				pos = menuWidth;
				$('#con').animate({left:((0-pos)+'px')});
			} else if(pos == menuWidth) {
				pos = mainWidth*1.0/3+menuWidth;
				$('#con').animate({left:((0-pos)+'px')});
			} else {
				pos = 0;
				$('#con').animate({left:((0-pos)+'px')});
			}
		});

		$('#message')[0].addEventListener('input', function() {
			var text = $('#message')[0].value;
			var num = 100-text.length;
			$('#remainNum')[0].innerHTML = "还剩 "+num+" 字";
		});
	});
	</script>
</body>
</html>
