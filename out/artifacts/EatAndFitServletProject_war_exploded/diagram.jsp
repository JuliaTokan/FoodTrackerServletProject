<html>
<head>
    <link rel="stylesheet" href="/static/css/diagram.css">
    <link rel="stylesheet" href="/static/css/progressbar.css">
</head>
<body>

<div class="single-chart">
    <svg viewBox="0 0 36 36" class="circular-chart orange">
        <path class="circle-bg"
              d="M18 2.0845
          a 15.9155 15.9155 0 0 1 0 31.831
          a 15.9155 15.9155 0 0 1 0 -31.831"
        />
        <path class="circle"
              stroke-dasharray="110, 100"
              d="M18 2.0845
          a 15.9155 15.9155 0 0 1 0 31.831
          a 15.9155 15.9155 0 0 1 0 -31.831"
        />
        <text x="18" y="20.35" class="percentage">70%</text>
    </svg>
</div>

<div class="mom">
    <div class="child">
        <div class="meterContainer childinner">
            <div aria-hidden="true" class="meterBackground">
                <div aria-hidden="true" class="meterBar">
                    <label class="progressValue">
                        <span class="progressNumber"></span>
                        <meter max="120" value="90" class="leaseMeter"></meter>
                    </label><!--progressValue-->
                </div><!--meterBar-->
            </div> <!--meterBackground-->
        </div><!--meterContainer-->
    </div>
    <div class="child">
        <div class="meterContainer childinner">
            <div aria-hidden="true" class="meterBackground">
                <div aria-hidden="true" class="meterBar">
                    <label class="progressValue">
                        <span class="progressNumber"></span>
                        <meter max="120" value="90" class="leaseMeter"></meter>
                    </label><!--progressValue-->
                </div><!--meterBar-->
            </div> <!--meterBackground-->
        </div><!--meterContainer-->
    </div><div class="child">
    <div class="meterContainer childinner">
        <div aria-hidden="true" class="meterBackground">
            <div aria-hidden="true" class="meterBar">
                <label class="progressValue">
                    <span class="progressNumber"></span>
                    <meter max="120" value="90" class="leaseMeter"></meter>
                </label><!--progressValue-->
            </div><!--meterBar-->
        </div> <!--meterBackground-->
    </div><!--meterContainer-->
</div>
</div>
</body>
<script src="/static/js/diagram.js"></script>
<script src="/static/js/progressbar.js"></script>
</html>