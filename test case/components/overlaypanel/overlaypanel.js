/**
 * PrimeFaces OverlayPanel Widget
 */
 (function (factory) {
     if (typeof define === 'function' && define.amd) {
         // AMD. Register as an anonymous module.
         define(['jquery'], factory);
     } else if (typeof module === 'object' && module.exports) {
         // Node/CommonJS
         module.exports = function( root, jQuery ) {
             factory(jQuery);
             return jQuery;
         };
     } else {
         // Browser globals
         factory(jQuery);
     }
 }(function ($) {

    $.widget("primeui.puioverlaypanel", {
       
        options: {
            target: null,
            showEvent: 'click',
            hideEvent: 'click',
            showCloseIcon: false,
            dismissable: true,
            my: 'left top',
            at: 'left bottom',
            preShow: null,
            postShow: null,
            onHide: null,
            shared: false,
            delegatedTarget: null
        },
        
        _create: function() {
            this.element.addClass('ui-overlaypanel ui-widget ui-widget-content ui-corner-all ui-shadow ui-helper-hidden');
            this.container = $('<div class="ui-overlaypanel-content"></div>').appendTo(this.element);
            this.container.append(this.element.contents());
            
            if(this.options.showCloseIcon) {
                this.closerIcon = $('<a href="#" class="ui-overlaypanel-close ui-state-default" href="#"><span class="fa fa-fw fa-close"></span></a>').appendTo(this.container);
            }
            
            this._bindCommonEvents();
            
            if(this.options.target) {
                this.target = $(this.options.target);
                this._bindTargetEvents();
            }
        },
        
        _bindCommonEvents: function() {
            var $this = this;
            
            if(this.options.showCloseIcon) {
                this.closerIcon.on('mouseover.puioverlaypanel', function() {
                    $(this).addClass('ui-state-hover');
                })
                .on('mouseout.puioverlaypanel', function() {
                    $(this).removeClass('ui-state-hover');
                })
                .on('click.puioverlaypanel', function(e) {
                    if($this._isVisible() ) {
                        $this.hide();
                    }
                    else {
                        $this.show();
                    }
                    e.preventDefault();
                });
            }
            
            //hide overlay when mousedown is at outside of overlay
            if(this.options.dismissable) {
                var hideNS = 'mousedown.' + this.id;
                $(document.body).off(hideNS).on(hideNS, function (e) {
                    if(!$this._isVisible()) {
                        return;
                    }

                    //do nothing on target mousedown
                    if($this.target) {
                        var target = $(e.target);
                        if($this.target.is(target)||$this.target.has(target).length > 0) {
                            return;
                        }
                    }

                    //hide overlay if mousedown is on outside
                    var offset = $this.element.offset();
                    if(e.pageX < offset.left ||
                        e.pageX > offset.left + $this.element.outerWidth() ||
                        e.pageY < offset.top ||
                        e.pageY > offset.top + $this.element.outerHeight()) {

                        $this.hide();
                    }
                }); 
            }

            //Hide overlay on resize
            var resizeNS = 'resize.' + this.id;
            $(window).off(resizeNS).on(resizeNS, function() {
                if($this._isVisible()) {
                    $this._align();
                }
            });
        },
        
        _bindTargetEvents: function() {
            var $this = this;

            //show and hide events for target
            if(this.options.showEvent === this.options.hideEvent) {
                var event = this.options.showEvent;
                
                if(this.options.shared) {
                    this.target.on(event, this.options.delegatedTarget, null, function(e) {
                        $this._toggle(e.currentTarget);
                    });
                }
                else {
                    this.target.on(event, function(e) {
                        $this._toggle();
                    });
                }
            }
            else {
                var showEvent = this.options.showEvent + '.puioverlaypanel',
                hideEvent = this.options.hideEvent + '.puioverlaypanel';
                
                if(this.options.shared) {
                    this.target.off(showEvent + '.puioverlaypanel' + ' ' + hideEvent + '.puioverlaypanel', this.options.delegatedTarget).on(showEvent, this.options.delegatedTarget, null, 
                            function(e) {
                                $this._onShowEvent(e);
                            })
                            .on(hideEvent, this.options.delegatedTarget, null, function(e) {
                                $this._onHideEvent();
                            });
                }
                else {
                    this.target.off(showEvent + '.puioverlaypanel' + ' ' + hideEvent + '.puioverlaypanel').on(showEvent, function(e) {
                        $this._onShowEvent(e);
                    })
                    .on(hideEvent, function(e) {
                        $this._onHideEvent();
                    });
                }
                
            }
            
            if(this.options.shared) {
                $this.target.off('keydown.puioverlaypanel keyup.puioverlaypanel', this.options.delegatedTarget).on('keydown.puioverlaypanel', this.options.delegatedTarget, null, function(e) {
                    $this._onTargetKeydown(e);
                })
                .on('keyup.puioverlaypanel', this.options.delegatedTarget, null, function(e) {
                    $this._onTargetKeyup(e);
                });
            }
            else {
                $this.target.off('keydown.puioverlaypanel keyup.puioverlaypanel').on('keydown.puioverlaypanel', function(e) {
                    $this._onTargetKeydown(e);
                })
                .on('keyup.puioverlaypanel', function(e) {
                    $this._onTargetKeyup(e);
                });
            }
        },
        
        _toggle: function(target) {
            if(this.options.shared) {
                this.show(target);
            }
            else {
                if(this._isVisible())
                    this.hide();
                else
                    this.show(target);
            }
            
        },
        
        _onShowEvent: function(e) {
            if(!this._isVisible()) {
                this.show(e.currentTarget);
                if(this.options.showEvent === 'contextmenu.puioverlaypanel') {
                    e.preventDefault();
                }
            }
        },
        
        _onHideEvent: function() {
            if(this._isVisible()) {
                this.hide();
            }
        },
        
        _onTargetKeydown: function(e) {
            var keyCode = $.ui.keyCode, key = e.which;
            
            if(key === keyCode.ENTER||key === keyCode.NUMPAD_ENTER) {
                e.preventDefault();
            }
        },
        
        _onTargetKeyup: function(e) {
            var keyCode = $.ui.keyCode, key = e.which;
            
            if(key === keyCode.ENTER||key === keyCode.NUMPAD_ENTER) {
                $this._toggle();
                e.preventDefault();
            }
        },
        
        _isVisible: function() {
            return this.element.css('visibility') == 'visible' && this.element.is(':visible');
        },
        
        show: function(target) {
            var $this = this;
            
            $this._trigger('preShow', null, {'target':target});
            
            this._align(target);

            //replace visibility hidden with display none for effect support, toggle marker class
            this.element.css({
                'display':'none',
                'visibility':'visible'
            });

            if(this.options.showEffect) {
                this.element.show(this.options.showEffect, {}, 200, function() {
                    $this.postShow();
                });
            }
            else {
                this.element.show();
                this.postShow();
            }
        },
        
        hide: function() {
            var $this = this;
            
            if(this.options.hideEffect) {
                this.element.hide(this.options.hideEffect, {}, 200, function() {
                    $this.postHide();
                });
            }
            else {
                this.element.hide();
                this.postHide();
            }
        },
        
        postShow: function() {
            this._trigger('postShow');
            
            this._applyFocus();
        },
        
        postHide: function() {
            //replace display block with visibility hidden for hidden container support, toggle marker class
            this.element.css({
                'display':'block',
                'visibility':'hidden'
            });
            
            this._trigger('onHide');
        },
        
        _align: function(target) {
            var win = $(window),
            ofTarget = target||this.target;

            this.element.css({'left':'', 'top':'', 'z-index': PUI.zindex})
                    .position({
                        my: this.options.my,
                        at: this.options.at,
                        of: ofTarget
                    });
        },
        
        _applyFocus: function() {
            this.element.find(':not(:submit):not(:button):input:visible:enabled:first').focus();
        }
        
    });
    
}));