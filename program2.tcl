set ns [new Simulator]

set namfile [open s2.nam w]
$ns namtrace-all $namfile
set tracefile [open s2.tr w]
$ns trace-all $tracefile
proc finish {} {
global ns namfile tracefile
$ns flush-trace
close $namfile
close $tracefile
exec nam s2.nam &
exec awk -f s2.awk s2.tr &
exit 0
}
set n0 [$ns node]
set n1 [$ns node]
set n2 [$ns node]
set n3 [$ns node]
set n4 [$ns node]
set n5 [$ns node]
set n6 [$ns node]

$ns duplex-link $n1 $n0 1Mb 10ms DropTail
$ns duplex-link $n2 $n0 1Mb 10ms DropTail
$ns duplex-link $n3 $n0 1.75Mb 20ms DropTail
$ns duplex-link $n4 $n0 1Mb 10ms DropTail
$ns duplex-link $n5 $n0 1Mb 10ms DropTail
$ns duplex-link $n6 $n0 1Mb 10ms DropTail

$ns duplex-link-op $n0 $n1 orient right
$ns duplex-link-op $n0 $n2 orient left
$ns duplex-link-op $n0 $n3 orient right-up
$ns duplex-link-op $n0 $n4 orient right-down
$ns duplex-link-op $n0 $n5 orient left-up
$ns duplex-link-op $n0 $n6 orient left-down

Agent/Ping instproc recv {from rtt} {
$self instvar node_
puts "node [$node_ id] received ping answer from $from with round-trip-time $rtt ms."
}
set P1 [new Agent/Ping]
set P2 [new Agent/Ping]
set P3 [new Agent/Ping]
set P4 [new Agent/Ping]
set P5 [new Agent/Ping]
set P6 [new Agent/Ping]

$ns attach-agent $n1 $P1
$ns attach-agent $n2 $P2
$ns attach-agent $n3 $P3
$ns attach-agent $n4 $P4
$ns attach-agent $n5 $P5
$ns attach-agent $n6 $P6
 
$ns queue-limit $n0 $n4 1
$ns queue-limit $n0 $n5 2
$ns queue-limit $n0 $n6 2

$ns connect $P1 $P4
$ns connect $P2 $P5
$ns connect $P3 $P6
$ns at 0.2 "$P1 send"
$ns at 0.4 "$P2 send"
$ns at 0.6 "$P3 send"
$ns at 1.0 "$P4 send"
$ns at 1.2 "$P5 send"
$ns at 1.4 "$P6 send"
$ns at 2.0 "finish"
$ns run