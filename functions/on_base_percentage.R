calculateOBP <- function(playerStats){
colnames(playerStats) <- c("H", "BB", "HBP", "AB", "SF")
h <- sum(playerStats[,"H"])
bb <- sum(playerStats[,"BB"])
hbp <- sum(playerStats[,"HBP"])
ab <- sum(playerStats[,"AB"])
sf <- sum(playerStats[,"SF"])
obp <- (h + bb + hbp) / (ab + bb + hbp + sf)
round(obp, 3)
}