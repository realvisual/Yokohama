(function() {
	/**
	 * @author ChaoticLife
	 * @param fontSize
	 * @returns
	 */
	String.prototype.getWidth = function(fontSize) {
		var span = document.getElementById("__getwidth");
		if (span == null) {
			span = document.createElement("span");
			span.id = "__getwidth";
			document.body.appendChild(span);
			span.style.visibility = "hidden";
			span.style.whiteSpace = "nowrap";
		}
		span.innerText = this;
		span.style.fontSize = fontSize + "px";

		return span.offsetWidth;
	}
})();
