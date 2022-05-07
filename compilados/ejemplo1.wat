(module
(type $_sig_i32i32i32 (func (param i32 i32 i32) ))
(type $_sig_i32ri32 (func (param i32) (result i32)))
(type $_sig_i32 (func (param i32)))
(type $_sig_ri32 (func (result i32)))
(type $_sig_void (func ))
(import "runtime" "print" (func $print (param i32)))
(import "runtime" "read" (func $read (result i32)))
(import "runtime" "exceptionHandler" (func $exception (type $_sig_i32)))
(memory 2000)
(global $SP (mut i32) (i32.const 0)) ;; start of stack
(global $MP (mut i32) (i32.const 0)) ;; mark pointer
(global $NP (mut i32) (i32.const 131071996)) ;; heap 2000*64*1024-4
(start $main)
(func $main (type $_sig_void)
(local $localsStart i32)
(local $temp i32)
i32.const 100
call $reserveStack  ;; returns old MP (dynamic link)
   set_local $temp
   get_global $MP
   get_local $temp
   i32.store
   get_global $MP
   get_global $SP
   i32.store offset=4
   get_global $MP
   i32.const 8
   i32.add
   set_local $localsStart
get_local $localsStart
i32.const 0
i32.add
i32.const 0
i32.store
get_local $localsStart
i32.const 4
i32.add
i32.const 0
i32.store offset=0
get_local $localsStart
i32.const 4
i32.add
i32.const 0
i32.store offset=4
get_local $localsStart
i32.const 4
i32.add
i32.const 0
i32.store offset=8
get_local $localsStart
i32.const 4
i32.add
i32.const 0
i32.store offset=12
get_local $localsStart
i32.const 4
i32.add
i32.const 0
i32.store offset=16
get_local $localsStart
i32.const 4
i32.add
i32.const 0
i32.store offset=20
get_local $localsStart
i32.const 4
i32.add
i32.const 0
i32.store offset=24
get_local $localsStart
i32.const 4
i32.add
i32.const 0
i32.store offset=28
get_local $localsStart
i32.const 4
i32.add
i32.const 0
i32.store offset=32
get_local $localsStart
i32.const 4
i32.add
i32.const 0
i32.store offset=36
get_local $localsStart
i32.const 4
i32.add
i32.const 0
i32.store offset=40
get_local $localsStart
i32.const 4
i32.add
i32.const 0
i32.store offset=44
get_local $localsStart
i32.const 4
i32.add
i32.const 0
i32.store offset=48
get_local $localsStart
i32.const 4
i32.add
i32.const 0
i32.store offset=52
get_local $localsStart
i32.const 4
i32.add
i32.const 0
i32.store offset=56
get_local $localsStart
i32.const 4
i32.add
i32.const 0
i32.store offset=60
get_local $localsStart
i32.const 4
i32.add
i32.const 0
i32.store offset=64
get_local $localsStart
i32.const 4
i32.add
i32.const 0
i32.store offset=68
get_local $localsStart
i32.const 4
i32.add
i32.const 0
i32.store offset=72
get_local $localsStart
i32.const 4
i32.add
i32.const 0
i32.store offset=76
get_local $localsStart
i32.const 84
i32.add
i32.const 10
i32.store
get_local $localsStart
i32.const 4
i32.add
i32.const 4
i32.const 0
i32.mul
i32.add
i32.const 1
i32.store
get_local $localsStart
i32.const 4
i32.add
i32.const 4
i32.const 1
i32.mul
i32.add
i32.const 1
i32.store
get_local $localsStart
i32.const 88
i32.add
i32.const 0
i32.store
block
 loop
get_local $localsStart
i32.const 88
i32.add
i32.load offset=0
get_local $localsStart
i32.const 84
i32.add
i32.load offset=0
i32.le_s
i32.eqz
br_if 1
get_local $localsStart
i32.const 88
i32.add
i32.load offset=0
i32.const 0
i32.eq
get_local $localsStart
i32.const 88
i32.add
i32.load offset=0
i32.const 1
i32.eq
i32.or
if
get_local $localsStart
i32.const 4
i32.add
i32.const 4
get_local $localsStart
i32.const 88
i32.add
i32.load offset=0
i32.mul
i32.add
i32.const 1
i32.store
else
get_local $localsStart
i32.const 4
i32.add
i32.const 4
get_local $localsStart
i32.const 88
i32.add
i32.load offset=0
i32.mul
i32.add
get_local $localsStart
i32.const 4
i32.add
i32.const 4
get_local $localsStart
i32.const 88
i32.add
i32.load offset=0
i32.const 1
i32.sub
i32.mul
i32.add
i32.load
get_local $localsStart
i32.const 4
i32.add
i32.const 4
get_local $localsStart
i32.const 88
i32.add
i32.load offset=0
i32.const 2
i32.sub
i32.mul
i32.add
i32.load
i32.add
i32.store
end
get_local $localsStart
i32.const 88
i32.add
get_local $localsStart
i32.const 88
i32.add
i32.load offset=0
i32.const 1
i32.add
i32.store
br 0
end
end
get_local $localsStart
i32.const 0
i32.add
get_local $localsStart
i32.const 4
i32.add
i32.const 4
get_local $localsStart
i32.const 84
i32.add
i32.load offset=0
i32.mul
i32.add
i32.load
i32.store
get_local $localsStart
i32.const 0
i32.add
i32.load offset=0
call $print
call $freeStack
)
(func $reserveStack (param $size i32)
   (result i32)
   get_global $MP
   get_global $SP
   set_global $MP
   get_global $SP
   get_local $size
   i32.add
   set_global $SP
   get_global $SP
   get_global $NP
   i32.gt_u
   if
   i32.const 3
   call $exception
   end
)
(func $freeStack (type $_sig_void)
   get_global $MP
   i32.load
   i32.load offset=4
   set_global $SP
   get_global $MP
   i32.load
   set_global $MP   
)
(func $reserveHeap (type $_sig_i32)
   (param $size i32)
;; ....
)
(func $copyn (type $_sig_i32i32i32) ;; copy $n i32 slots from $src to $dest
   (param $src i32)
   (param $dest i32)
   (param $n i32)
   block
     loop
       get_local $n
       i32.eqz
       br_if 1
       get_local $n
       i32.const 1
       i32.sub
       set_local $n
       get_local $dest
       get_local $src
       i32.load
       i32.store
       get_local $dest
       i32.const 4
       i32.add
       set_local $dest
       get_local $src
       i32.const 4
       i32.add
       set_local $src
       br 0
     end
   end
)(export "main" (func $main))
)