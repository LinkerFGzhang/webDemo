<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta charset="UTF-8">
<link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
<title>Index</title>
</head>
<body>
    <div style="padding: 100px 100px 1px;" >
    <form class="bs-example bs-example-form" role="form">
        <div class="row">
            <div class="col-lg-6">
                <div class="input-group">
                    <input class="form-control" type="text" id="content" onkeyup="searchlike()" required>                   
                    <span class="input-group-btn">
                        <button class="btn btn-default" type="button">Go!</button>
                    </span>                   
                </div>
            </div>
        </div>       
    </form>
</div>
<div style="padding: 0px 100px 10px;"  id='search-result'></div>
</body>
<script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
<script src="http://libs.baidu.com/jquery/2.1.1/jquery.min.js"></script>
<script type="text/javascript">
window.jQuery||document.wirte('<script src="/test/js/jquery-2.1.1.min.js"><\/script>')
</script>
<script src="/test/js/index.js"></script>
</html>