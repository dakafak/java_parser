package test.parser.parser_api;

import main.parser.parser_api.ParserInterface;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ParserTest {

	ParserInterface parserInterface;

	@Before
	public void init(){
		parserInterface = new ParserInterface();
	}

	@Test
	public void parseClassStringTest() {

	}

	@Test
	public void parseSingleLineCommentsTest(){
		Assert.assertNotNull(parserInterface.parseSingleLineCommentNode(testString));
	}

	String testString = "/*\n" +
			" * To change this license header, choose License Headers in Project Properties.\n" +
			" * To change this template file, choose Tools | Templates\n" +
			" * and open the template in the editor.\n" +
			" */\n" +
			"package Game;\n" +
			"\n" +
			"import Building.Entity;\n" +
			"import Building.Roads;\n" +
			"import Fighting.Effect;\n" +
			"import Fighting.Projectile;\n" +
			"import Story.Quest;\n" +
			"import Story.QuestManager;\n" +
			"import Tools.ArrayList2D;\n" +

			"import static Tools.EMath.fullIntersects;\n" +
			"import Tools.GType;\n" +
			"import static Tools.GType.CHUNK_SIZE;\n" +
			"import static Tools.GType.ENTITY_GOLD;\n" +
			"import static Tools.GType.ENTITY_HEALTH;\n" +
			"import static Tools.GType.ENTITY_HEALTHPOT;\n" +
			"import static Tools.GType.ENTITY_ROCK;\n" +
			"import static Tools.GType.ENTITY_WOOD;\n" +
			"import static Tools.GType.GAME_CONNECTEDNESS;\n" +
			"import static Tools.GType.IMAGE_TILE_SIZE;\n" +
			"import static Tools.GType.PP_NONE;\n" +
			"import static Tools.GType.SPECIAL_SMITH;\n" +
			"import static Tools.GType.SPECIAL_SPAWN;\n" +
			"import static Tools.GType.STANDARD_SCALE_DRAW_HEIGHT;\n" +
			"import static Tools.GType.STANDARD_SCALE_DRAW_WIDTH;\n" +
			"import static Tools.GType.TEAM_PLAYER;\n" +
			"import static Tools.GType.chunkHeightDistance;\n" +
			"import static Tools.GType.chunkWidthDistance;\n" +
			"import static Tools.Tool.WRITE;\n" +
			"import java.awt.Color;\n" +
			"import java.awt.Font;\n" +
			"import java.awt.FontFormatException;\n" +
			"import java.awt.Graphics;\n" +
			"import java.awt.Graphics2D;\n" +
			"import java.awt.GraphicsEnvironment;\n" +
			"import java.awt.IllegalComponentStateException;\n" +
			"import java.awt.Image;\n" +
			"import java.awt.MouseInfo;\n" +
			"import java.awt.Point;\n" +
			"import java.awt.event.ActionEvent;\n" +
			"import java.awt.event.ActionListener;\n" +
			"import java.awt.event.KeyEvent;\n" +
			"import java.awt.event.KeyListener;\n" +
			"import java.awt.event.MouseEvent;\n" +
			"import java.awt.event.MouseListener;\n" +
			"import java.awt.geom.AffineTransform;\n" +
			"import java.awt.image.AffineTransformOp;\n" +
			"import java.awt.image.BufferedImage;\n" +
			"import java.io.BufferedReader;\n" +
			"import java.io.File;\n" +
			"import java.io.FileNotFoundException;\n" +
			"import java.io.FileReader;\n" +
			"import java.io.IOException;\n" +
			"import java.io.Serializable;\n" +
			"import java.util.ArrayList;\n" +
			"import java.util.HashMap;\n" +
			"import java.util.logging.Level;\n" +
			"import java.util.logging.Logger;\n" +
			"import javax.swing.ImageIcon;\n" +
			"import javax.swing.JFrame;\n" +
			"import javax.swing.JPanel;\n" +
			"import javax.swing.Timer;\n" +
			"\n" +
			"/**\n" +
			" *\n" +
			" * @author Dakafak\n" +
			" */\n" +
			"public class Game extends JPanel implements ActionListener, Serializable{\n" +
			"\tprivate static boolean paused;\n" +
			"\tprivate boolean displayingMap;\n" +
			"\tprivate boolean showDeveloperStats;\n" +
			"\t\n" +
			"\tpublic String saveGameName;\n" +
			"\t\n" +
			"\tboolean closeDown, gameOver;\n" +
			"\tThread graphicsThread, enemyThread, chunkThread;\n" +
			"\tTimer update;\n" +
			"\t\n" +
			"\tArrayList2D map;\n" +
			"\tPlayer player;\n" +
			"\t\n" +
			"\tfloat scale, screenWidth, screenHeight;\n" +
			"\t//double offsetX, offsetY;\n" +
			"\tint chunkSize;\n" +
			"\t\n" +
			"\t//some biome list stuff temp\n" +
			"\tArrayList<Biome> biomeList;\n" +
			"\t//----------\n" +
			"\t\n" +
			"\t//for graphics stuff\n" +
			"\tFont defaultFont;\n" +
			"\tFont defaultFont24;\n" +
			"\tFont defaultFont48;\n" +
			"\t\n" +
			"\t/*\n" +
			"\t\tFor roads and cities\n" +
			"\t*/\n" +
			"\tRoads roadManager;\n" +
			"\tArrayList<Town> towns;\n" +
			"\t\n" +
			"\t//for quests and story\n" +
			"\tQuestManager quests;\n" +
			"\t\n" +
			"\tpublic Game(Player player, ArrayList2D map, ArrayList<Town> towns, int width, int height, JFrame frame, float scale, String saveGameName){\n" +
			"\t\tdisplayingMap = false;\n" +
			"\t\tpaused = false;\n" +
			"\t\t\n" +
			"\t\tthis.player = player;\n" +
			"\t\tthis.map = map;\n" +
			"\t\tthis.scale = scale;\n" +
			"\t\tthis.saveGameName = saveGameName;\n" +
			"\t\tthis.towns = towns;\n" +
			"\t\tsuper.setBackground(Color.BLACK);\n" +
			"\t\t\n" +
			"\t\troadManager = new Roads();\n" +
			"\t\t\n" +
			"//\t\tif(quests != null){\n" +
			"//\t\t\tthis.quests = quests;\n" +
			"//\t\t}\n" +
			"//\t\telse{\n" +
			"\t\t\tthis.quests = new QuestManager(player.currentQuests);\n" +
			"//\t\t}\n" +
			"\t\t\n" +
			"\t\tlastUpdate = System.nanoTime();\n" +
			"\t\tcurrentTime = System.nanoTime();\n" +
			"\t\ttime = 0;\n" +
			"\t\t\n" +
			"\t\tsetupFonts();\n" +
			"\t\tsetupImageMaps();\n" +
			"\t\tsetupEvents(frame);\n" +
			"\t\tsetupBiomes();\n" +
			"\t\tsetupEffects();\n" +
			"\t\tsetupEntities();\n" +
			"\t\tsetupGame(width, height);\n" +
			"\t\tsetupCharLevel();\n" +
			"\t\t//fixPlayerChunkEnemies();\n" +
			"\t\tsetupMinimapVariables();\n" +
			"\t\t//setupCrafting();\n" +
			"\t\t\n" +
			"\t\t//setPlayerCamp();\n" +
			"\t\t\n" +
			"\t\tplayerProjectiles = new ArrayList<>();\n" +
			"\t\tenemyProjectiles = new ArrayList<>();\n" +
			"\t\tsetupProjectileImages();//clear enemies from inital player spawn so enemies don't spawn on player\n" +
			"\t\t\n" +
			"\t\t//player.setupImages(scale, this);\n" +
			"\t\tsetupBufferedImages();\n" +
			"\t\tsetupCharacterImages(scale, this);\n" +
			"\t\t//setupInventory();\n" +
			"//\t\ttestPath();\n" +
			"\t}\n" +
			"\t\n" +
			"\t//for character images\n" +
			"\t//<editor-fold defaultstate=\"collapsed\">\n" +
			"\t\tImage[] characterImages;\n" +
			"\t\tBufferedImage[] characterMovement;\n" +
			"\t\t\n" +
			"\t\t//for setting up character images\n" +
			"\t\t//<editor-fold defaultstate=\"collapsed\">\n" +
			"\t\tpublic void setupCharacterImages(double scale, JPanel panel){\n" +
			"\t\t\tcharacterImages = new Image[] {(new ImageIcon(\"images/character/movement/move_01.png\")).getImage(),\n" +
			"\t\t\t\t\t\t\t\t\t(new ImageIcon(\"images/character/movement/move_02.png\")).getImage(),\n" +
			"\t\t\t\t\t\t\t\t\t(new ImageIcon(\"images/character/movement/move_03.png\")).getImage(),\n" +
			"\t\t\t\t\t\t\t\t\t(new ImageIcon(\"images/character/movement/attack_01.png\")).getImage(),\n" +
			"\t\t\t\t\t\t\t\t\t(new ImageIcon(\"images/character/movement/attack_02.png\")).getImage()\n" +
			"\t\t\t\t\t\t\t\t\t\t};\n" +
			"\t\t\tcharacterMovement = new BufferedImage[characterImages.length];\n" +
			"\t\t\tfor(int i=0; i<characterImages.length; i++){\n" +
			"\t\t\t\tcharacterMovement[i] = new BufferedImage((int)(scale * 2), (int)(scale * 2), BufferedImage.TYPE_INT_ARGB);\n" +
			"\t\t\t\tGraphics2D g2d = characterMovement[i].createGraphics();\n" +
			"\t\t\t\tg2d.drawImage(characterImages[i], (int) (scale/2), (int) (scale/2), (int) (scale * 1), (int) (scale * 1), panel);\n" +
			"\t\t\t}\n" +
			"\t\t}\n" +
			"\t\t//</editor-fold>\n" +
			"\t//</editor-fold>\n" +
			"\t\n" +
			"//\tpublic void saveThatShit(){\n" +
			"//\t\tWRITE(\"----------SAVING with save game name of: \" + saveGameName);\n" +
			"//\t\tFile saveFile = new File(\"saves/\" + saveGameName + \"/\" + saveGameName + \".map\");\n" +
			"//\t\tif(!saveFile.exists()){\n" +
			"//\t\t\ttry {\n" +
			"//\t\t\t\tsaveFile.createNewFile();\n" +
			"//\t\t\t} catch (IOException ex) {\n" +
			"//\t\t\t\tLogger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);\n" +
			"//\t\t\t}\n" +
			"//\t\t}\n" +
			"//\t\t\n" +
			"//\t\ttry {\n" +
			"//\t\t\tFileOutputStream fileOut = new FileOutputStream(saveFile);\n" +
			"//\t\t\tObjectOutputStream objectOut = new ObjectOutputStream(fileOut);\n" +
			"//\t\t\tobjectOut.writeObject(map);\n" +
			"//\t\t\tobjectOut.flush();\n" +
			"//\t\t\tobjectOut.close();\n" +
			"//\t\t\tfileOut.close();\n" +
			"//\t\t} catch (IOException ex) {\n" +
			"//\t\t\tLogger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);\n" +
			"//\t\t}\n" +
			"//\t\t\n" +
			"//\t\tFile questsFile = new File(\"saves/\" + saveGameName + \"/\" + saveGameName + \".quests\");\n" +
			"//\t\tif(!questsFile.exists()){\n" +
			"//\t\t\ttry {\n" +
			"//\t\t\t\tquestsFile.createNewFile();\n" +
			"//\t\t\t} catch (IOException ex) {\n" +
			"//\t\t\t\tLogger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);\n" +
			"//\t\t\t}\n" +
			"//\t\t}\n" +
			"//\t\ttry {\n" +
			"//\t\t\tFileOutputStream fileOut = new FileOutputStream(questsFile);\n" +
			"//\t\t\tObjectOutputStream objectOut = new ObjectOutputStream(fileOut);\n" +
			"//\t\t\tobjectOut.writeObject(quests);\n" +
			"//\t\t\tobjectOut.flush();\n" +
			"//\t\t\tobjectOut.close();\n" +
			"//\t\t\tfileOut.close();\n" +
			"//\t\t} catch (IOException ex) {\n" +
			"//\t\t\tLogger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);\n" +
			"//\t\t}\n" +
			"//\t\t\n" +
			"//\t\tFile playerFile = new File(\"saves/\" + saveGameName + \"/\" + saveGameName + \".player\");\n" +
			"//\t\tif(!playerFile.exists()){\n" +
			"//\t\t\ttry {\n" +
			"//\t\t\t\tplayerFile.createNewFile();\n" +
			"//\t\t\t} catch (IOException ex) {\n" +
			"//\t\t\t\tLogger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);\n" +
			"//\t\t\t}\n" +
			"//\t\t}\n" +
			"//\t\ttry {\n" +
			"//\t\t\tFileOutputStream fileOut = new FileOutputStream(playerFile);\n" +
			"//\t\t\tObjectOutputStream objectOut = new ObjectOutputStream(fileOut);\n" +
			"//\t\t\tobjectOut.writeObject(player);\n" +
			"//\t\t\tobjectOut.flush();\n" +
			"//\t\t\tobjectOut.close();\n" +
			"//\t\t\tfileOut.close();\n" +
			"//\t\t} catch (IOException ex) {\n" +
			"//\t\t\tLogger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);\n" +
			"//\t\t}\n" +
			"//\t}\n" +
			"\t\n" +
			"\tpublic static int pause(){\n" +
			"\t\tif(!paused){\n" +
			"\t\t\tpaused = true;\n" +
			"\t\t\treturn 1;\n" +
			"\t\t}\n" +
			"\t\treturn 0;\n" +
			"\t}\n" +
			"\t\n" +
			"\tpublic static int unpause(){\n" +
			"\t\tif(paused){\n" +
			"\t\t\tpaused = false;\n" +
			"\t\t\treturn 1;\n" +
			"\t\t}\n" +
			"\t\treturn 0;\n" +
			"\t}\n" +
			"\t\n" +
			"\tprivate void setupFonts(){\n" +
			"\t\ttry {\n" +
			"\t\t\tdefaultFont = Font.createFont(Font.TRUETYPE_FONT, new File(\"fonts/Cornerstone.ttf\"));\n" +
			"\t\t\tGraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();\n" +
			"\t\t\tge.registerFont(defaultFont);\n" +
			"\t\t\tdefaultFont24 = defaultFont.deriveFont(24f);\n" +
			"\t\t\tdefaultFont48 = defaultFont.deriveFont(36f);\n" +
			"\t   } catch (IOException|FontFormatException e) {\n" +
			"\t\t\t//Handle exception\n" +
			"\t\t\tWRITE(\"FONT ERROR - Was not able to load custom font\");\n" +
			"\t\t\tdefaultFont = new Font(\"Monospaced\", 0, 24);\n" +
			"\t\t}\n" +
			"\t}\n" +
			"\t\n" +
			"\tprivate void setupMinimapVariables(){\n" +
			"\t\tminimapWidth = minimapWidth * screenWidth;\n" +
			"\t\tminimapHeight = minimapHeight * screenWidth;\n" +
			"\t}\n" +
			"\t\n" +
			"//\tprivate void setPlayerCamp(){//setup story mode beginning\n" +
			"//\t\t//fix player location\n" +
			"//\t\tif(map.getFromCoord(0, 0) == null){\n" +
			"//\t\t\tWRITE(\"--------------The map is not generated yet------------------\");\n" +
			"//\t\t\t\n" +
			"//\t\t\t//generate new chunk\n" +
			"//\t\t\tChunk newChunk = new Chunk();\n" +
			"//\t\t\tnewChunk.generateChunk(map.toArray(), biomeList, roadManager, map.getIndexX(0), map.getIndexY(0), 0, 0, GAME_CONNECTEDNESS);\n" +
			"//\t\t\troadManager.setCampOnChunk(newChunk.grid, 0, 0);\n" +
			"//\t\t\tnewChunk.completeSetup(scale, time);\n" +
			"//\t\t\tnewChunk.initialSpawn(scale, time);\n" +
			"//\t\t\t\n" +
			"//\t\t\t//newChunk.grid[0][0] = null;\n" +
			"//\t\t\tfor(int j=0; j<newChunk.grid.length; j++){\n" +
			"//\t\t\t\tfor(int i=0; i<newChunk.grid[0].length; i++){\n" +
			"//\t\t\t\t\tnewChunk.grid[j][i].decor = -1;\n" +
			"//\t\t\t\t}\n" +
			"//\t\t\t}\n" +
			"////\t\t\t//find player spawn location\n" +
			"////\t\t\tboolean done = false;\n" +
			"////\t\t\tfor(int j=0; j<newChunk.grid.length; j++){\n" +
			"////\t\t\t\tfor(int i=0; i<newChunk.grid[0].length; i++){\n" +
			"////\t\t\t\t\tif(newChunk.grid[j][i].physicalProperties == PP_NONE){\n" +
			"////\t\t\t\t\t\tplayer.x = i;\n" +
			"////\t\t\t\t\t\tplayer.y = j;\n" +
			"////\t\t\t\t\t\tdone = true;\n" +
			"////\t\t\t\t\t\tbreak;\n" +
			"////\t\t\t\t\t}\n" +
			"////\t\t\t\t}\n" +
			"////\t\t\t\tif(done){\n" +
			"////\t\t\t\t\tbreak;\n" +
			"////\t\t\t\t}\n" +
			"////\t\t\t}\n" +
			"//\t\t\t\t\n" +
			"//\t\t\tmap.set(newChunk, 0, 0);\n" +
			"//\t\t}\n" +
			"//\t}\n" +
			"\t\n" +
			"//\tprivate void fixPlayerChunkEnemies(){\n" +
			"//\t\tfor(int i=-1; i<=1; i++){\n" +
			"//\t\t\tfor(int j=-1; j<=1; j++){\n" +
			"//\t\t\t\tChunk currentChunk = map.getFromCoord(getMapChunkX() + i, getMapChunkY() + j);\n" +
			"//\t\t\t\tif(currentChunk != null){\n" +
			"//\t\t\t\t\tif(currentChunk.units != null){\n" +
			"//\t\t\t\t\t\tcurrentChunk.units.clear();\n" +
			"//\t\t\t\t\t}\n" +
			"//\t\t\t\t}\n" +
			"//\t\t\t}\n" +
			"//\t\t}\n" +
			"//\t}\n" +
			"\t\n" +
			"\tpublic void saveGame(){\n" +
			"\t\t//------------- for quests ------------------------\n" +
			"\t\tString currentQuests = \"\";\n" +
			"\t\tfor(int i=0; i<quests.getNumberOfCurrentQuests(); i++){\n" +
			"\t\t\tcurrentQuests += quests.getCurrentQuest(i).id + \",\";\n" +
			"\t\t}\n" +
			"\t\tif(currentQuests.equals(\"\")){\n" +
			"\t\t\tcurrentQuests = \"\" + quests.currentQuest;\n" +
			"\t\t}\n" +
			"\t\t//-------------for town saving test\n" +
			"\t\t//Town town1 = new Town(\"dood1:1,2.1,1\");\n" +
			"\t\t//Town town2 = new Town(\"dood2:3,1.2,1.1,1\");\n" +
			"\t\t\n" +
			"\t\t//towns.add(town1);\n" +
			"\t\t//towns.add(town2);\n" +
			"\t\t\n" +
			"\t\t//----------- for town saving----------------\n" +
			"\t\tString townString = \"\";\n" +
			"\t\tfor(int i=0; i<towns.size(); i++){\n" +
			"\t\t\ttownString += towns.get(i);\n" +
			"\t\t\tif(i != towns.size() - 1){\n" +
			"\t\t\t\ttownString += \"|\";\n" +
			"\t\t\t}\n" +
			"\t\t}\n" +
			"\t\t\n" +
			"\t\t//--------------- save game---------------------\n" +
			"\t\tTools.FileManager.saveGame(saveGameName, map, player, currentQuests, townString);\n" +
			"\t}\n" +
			"\t\n" +
			"\tpublic void shutDown(){\n" +
			"\t\t//WRITE(\"trying to shut down\");\n" +
			"\t\tcloseDown = true;\n" +
			"\t\tgameOver = true;\n" +
			"\t\ttry {\n" +
			"\t\t\tenemyThread.join(1000);\n" +
			"\t\t\tchunkThread.join(1000);\n" +
			"\t\t\tgraphicsThread.join(1000);\n" +
			"\t\t} catch (InterruptedException ex) {\n" +
			"\t\t\tWRITE(\"was not able to join thread\");\n" +
			"\t\t}\n" +
			"\t}\n" +
			"\t\n" +
			"\t//image information for tiles\n" +
			"\t//<editor-fold defaultstate=\"collapsed\">\n" +
			"\tprivate void setupImageMaps(){\n" +
			"\t\tBufferedReader br = null, br2 = null, br3 = null;\n" +
			"\t\ttry {\n" +
			"\t\t\tfloorImages = new HashMap<>();\n" +
			"\t\t\tdecorImages = new HashMap<>();\n" +
			"\t\t\tentImages = new HashMap<>();\n" +
			"\t\t\teid = new HashMap<>();\n" +
			"\t\t\t\n" +
			"\t\t\t//floor\n" +
			"\t\t\t//<editor-fold defaultstate=\"collapsed\">\n" +
			"\t\t\tbr = new BufferedReader(new FileReader(new File(\"images/tiles/floor.info\")));\n" +
			"\t\t\tString readLine = \"\";\n" +
			"\t\t\twhile( (readLine = br.readLine()) != null ){\n" +
			"\t\t\t\tif(readLine.startsWith(\"//\")){\n" +
			"\t\t\t\t\tcontinue;\n" +
			"\t\t\t\t}\n" +
			"\t\t\t\tString[] splitLine = readLine.split(\":\");\n" +
			"\t\t\t\tif(splitLine.length == 2){\n" +
			"\t\t\t\t\tint id = Tools.Parse.getIntParse(splitLine[0]);\n" +
			"\t\t\t\t\tImage pairedImage = (new ImageIcon(\"images/tiles/\" + splitLine[1])).getImage();\n" +
			"\t\t\t\t\tfloorImages.put(id, pairedImage);\n" +
			"\t\t\t\t\tWRITE(\"setup new floor tile with: \" + splitLine[1] + \" to id \" + id);\n" +
			"\t\t\t\t}\n" +
			"\t\t\t}\n" +
			"\t\t\t//</editor-fold>\n" +
			"\t\t\t\n" +
			"\t\t\t//decor\n" +
			"\t\t\t//<editor-fold defaultstate=\"collapsed\">\n" +
			"\t\t\tbr2 = new BufferedReader(new FileReader(new File(\"images/tiles/decor.info\")));\n" +
			"\t\t\tString readLine2 = \"\";\n" +
			"\t\t\twhile( (readLine2 = br2.readLine()) != null ){\n" +
			"\t\t\t\tif(readLine2.startsWith(\"//\")){\n" +
			"\t\t\t\t\tcontinue;\n" +
			"\t\t\t\t}\n" +
			"\t\t\t\tString[] splitLine = readLine2.split(\":\");\n" +
			"\t\t\t\tif(splitLine.length == 2){\n" +
			"\t\t\t\t\tint id = Tools.Parse.getIntParse(splitLine[0]);\n" +
			"\t\t\t\t\tImage pairedImage = (new ImageIcon(\"images/tiles/\" + splitLine[1])).getImage();\n" +
			"\t\t\t\t\tdecorImages.put(id, pairedImage);\n" +
			"\t\t\t\t\tWRITE(\"setup new floor tile with: \" + splitLine[1] + \" to id \" + id);\n" +
			"\t\t\t\t}\n" +
			"\t\t\t}\n" +
			"\t\t\t//</editor-fold>\n" +
			"\t\t\t\n" +
			"\t\t\t//entities\n" +
			"\t\t\t//<editor-fold defaultstate=\"collapsed\">\n" +
			"\t\t\tbr3 = new BufferedReader(new FileReader(new File(\"images/entities/ent.info\")));\n" +
			"\t\t\tString readLine3 = \"\";\n" +
			"\t\t\twhile( (readLine3 = br3.readLine()) != null){\n" +
			"\t\t\t\tif(readLine3.startsWith(\"//\")){\n" +
			"\t\t\t\t\tcontinue;\n" +
			"\t\t\t\t}\n" +
			"\t\t\t\t\n" +
			"\t\t\t\tString[] splitLine = readLine3.split(\":\");\n" +
			"\t\t\t\tif(splitLine.length == 3){\n" +
			"\t\t\t\t\tint id = Tools.Parse.getIntParse(splitLine[0]);\n" +
			"\t\t\t\t\tImage pairedImage = (new ImageIcon(\"images/entities/\" + splitLine[1])).getImage();\n" +
			"\t\t\t\t\tint entityID = Tools.Parse.getIntParse(splitLine[2]);\n" +
			"\t\t\t\t\t\n" +
			"\t\t\t\t\tentImages.put(id, pairedImage);\n" +
			"\t\t\t\t\tthis.eid.put(id, entityID);\n" +
			"\t\t\t\t\t\n" +
			"\t\t\t\t\tWRITE(\"setup new entity connecting: \" + splitLine[1] + \" to id \" + id);\n" +
			"\t\t\t\t}\n" +
			"\t\t\t}\n" +
			"\t\t\t//</editor-fold>\n" +
			"\t\t\t\n" +
			"\t\t} catch (FileNotFoundException ex) {\n" +
			"\t\t\tWRITE(\"ERROR - could not create buffered reader for image map setup\");\n" +
			"\t\t} catch (IOException ex) {\n" +
			"\t\t\tWRITE(\"ERROR - Could not read line for image map setup\");\n" +
			"\t\t} finally {\n" +
			"\t\t\ttry {\n" +
			"\t\t\t\tbr.close();\n" +
			"\t\t\t\tbr2.close();\n" +
			"\t\t\t} catch (IOException ex) {\n" +
			"\t\t\t\tLogger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);\n" +
			"\t\t\t}\n" +
			"\t\t}\n" +
			"\t}\n" +
			"\t//</editor-fold>\n" +
			"\t\n" +
			"\t/*\n" +
			"\t\tsetting up images for projectiles \n" +
			"\t*/\n" +
			"//\tBufferedImage[][] daggerBufferedImages, axeBufferedImages;\n" +
			"\tImage[] daggerImages, axeImages;\n" +
			"\t\n" +
			"\tImage[] meleeImages, rangedImages, vendorUnitImages, questUnitImages, civImages;\n" +
			"\tBufferedImage[][] meleeMovement, rangedMovement, vendorUnitMovement, questUnitMovement, civMovement;\n" +
			"\t//Image[][] meleeEnemy, rangedEnemy;\n" +
			"\t//<editor-fold defaultstate=\"collapsed\">\n" +
			"\t\tprivate void setupBufferedImages(){\n" +
			"\t\t\t//projectiles\n" +
			"\t\t\tdaggerImages = new Image[] {(new ImageIcon(\"images/enemy/weapon/dagger1.png\")).getImage(),\n" +
			"\t\t\t\t\t\t\t\t\t\t(new ImageIcon(\"images/enemy/weapon/dagger2.png\")).getImage(),\n" +
			"\t\t\t\t\t\t\t\t\t\t(new ImageIcon(\"images/enemy/weapon/dagger3.png\")).getImage(),\n" +
			"\t\t\t\t\t\t\t\t\t\t(new ImageIcon(\"images/enemy/weapon/dagger4.png\")).getImage()\n" +
			"\t\t\t\t\t\t\t\t\t};\n" +
			"\t\t\taxeImages = new Image[] {\t(new ImageIcon(\"images/character/weapon/ammo1.png\")).getImage(),\n" +
			"\t\t\t\t\t\t\t\t\t\t(new ImageIcon(\"images/character/weapon/ammo2.png\")).getImage(),\n" +
			"\t\t\t\t\t\t\t\t\t\t(new ImageIcon(\"images/character/weapon/ammo3.png\")).getImage(),\n" +
			"\t\t\t\t\t\t\t\t\t\t(new ImageIcon(\"images/character/weapon/ammo4.png\")).getImage()\n" +
			"\t\t\t\t\t\t\t\t\t};\n" +
			"\t\t\t\n" +
			"\t\t\t//enemies\n" +
			"\t\t\tsetupEnemyImages();\n" +
			"\t\t\t//load sprite sheet for both melee and ranged and create a image array/2darray that contains all images\n" +
			"\t\t}\n" +
			"\t\t\n" +
			"\t\t//code for enemy images\n" +
			"\t\t//<editor-fold defaultstate=\"collapsed\">\n" +
			"\t\tpublic void setupEnemyImages(){\n" +
			"\t\t\t//do something with type for each enemy images\n" +
			"\t\t\t//-------------------- for regular images----------------------------------\n" +
			"\t\t\tmeleeImages = new Image[] {\t(new ImageIcon(\"images/enemy/movement/melee/melee1.png\")).getImage(),\n" +
			"\t\t\t\t\t\t\t\t\t(new ImageIcon(\"images/enemy/movement/melee/melee2.png\")).getImage(),\n" +
			"\t\t\t\t\t\t\t\t\t(new ImageIcon(\"images/enemy/movement/melee/melee3.png\")).getImage(),\n" +
			"\t\t\t\t\t\t\t\t\t(new ImageIcon(\"images/enemy/movement/melee/melee4.png\")).getImage(),\n" +
			"\t\t\t\t\t\t\t\t\t(new ImageIcon(\"images/enemy/movement/melee/attack1.png\")).getImage(),\n" +
			"\t\t\t\t\t\t\t\t\t(new ImageIcon(\"images/enemy/movement/melee/attack2.png\")).getImage()\n" +
			"\t\t\t\t\t\t\t\t\t\t};\n" +
			"\t\t\trangedImages = new Image[] {\t(new ImageIcon(\"images/enemy/movement/ranged/ranged1.png\")).getImage(),\n" +
			"\t\t\t\t\t\t\t\t\t\t(new ImageIcon(\"images/enemy/movement/ranged/ranged2.png\")).getImage(),\n" +
			"\t\t\t\t\t\t\t\t\t\t(new ImageIcon(\"images/enemy/movement/ranged/ranged3.png\")).getImage(),\n" +
			"\t\t\t\t\t\t\t\t\t\t(new ImageIcon(\"images/enemy/movement/ranged/ranged4.png\")).getImage(),\n" +
			"\t\t\t\t\t\t\t\t\t\t(new ImageIcon(\"images/enemy/movement/ranged/attack1.png\")).getImage(),\n" +
			"\t\t\t\t\t\t\t\t\t\t(new ImageIcon(\"images/enemy/movement/ranged/attack2.png\")).getImage()\n" +
			"\t\t\t\t\t\t\t\t\t\t};\n" +
			"\t\t\t\n" +
			"\t\t\tvendorUnitImages = new Image[] {\t(new ImageIcon(\"images/enemy/movement/vendor/temp.png\")).getImage(),\n" +
			"\t\t\t\t\t\t\t\t\t\t(new ImageIcon(\"images/enemy/movement/vendor/temp.png\")).getImage(),\n" +
			"\t\t\t\t\t\t\t\t\t\t(new ImageIcon(\"images/enemy/movement/vendor/temp.png\")).getImage(),\n" +
			"\t\t\t\t\t\t\t\t\t\t(new ImageIcon(\"images/enemy/movement/vendor/temp.png\")).getImage(),\n" +
			"\t\t\t\t\t\t\t\t\t\t(new ImageIcon(\"images/enemy/movement/vendor/temp.png\")).getImage(),\n" +
			"\t\t\t\t\t\t\t\t\t\t(new ImageIcon(\"images/enemy/movement/vendor/temp.png\")).getImage()\n" +
			"\t\t\t\t\t\t\t\t\t\t};\n" +
			"\t\t\t\n" +
			"\t\t\tquestUnitImages = new Image[] {\t(new ImageIcon(\"images/enemy/movement/quest/temp.png\")).getImage(),\n" +
			"\t\t\t\t\t\t\t\t\t\t(new ImageIcon(\"images/enemy/movement/quest/temp.png\")).getImage(),\n" +
			"\t\t\t\t\t\t\t\t\t\t(new ImageIcon(\"images/enemy/movement/quest/temp.png\")).getImage(),\n" +
			"\t\t\t\t\t\t\t\t\t\t(new ImageIcon(\"images/enemy/movement/quest/temp.png\")).getImage(),\n" +
			"\t\t\t\t\t\t\t\t\t\t(new ImageIcon(\"images/enemy/movement/quest/temp.png\")).getImage(),\n" +
			"\t\t\t\t\t\t\t\t\t\t(new ImageIcon(\"images/enemy/movement/quest/temp.png\")).getImage()\n" +
			"\t\t\t\t\t\t\t\t\t\t};\n" +
			"\t\t\tcivImages = new Image[] {\t(new ImageIcon(\"images/enemy/movement/civ/temp.png\")).getImage(),\n" +
			"\t\t\t\t\t\t\t\t\t\t(new ImageIcon(\"images/enemy/movement/civ/temp.png\")).getImage(),\n" +
			"\t\t\t\t\t\t\t\t\t\t(new ImageIcon(\"images/enemy/movement/civ/temp.png\")).getImage(),\n" +
			"\t\t\t\t\t\t\t\t\t\t(new ImageIcon(\"images/enemy/movement/civ/temp.png\")).getImage(),\n" +
			"\t\t\t\t\t\t\t\t\t\t(new ImageIcon(\"images/enemy/movement/civ/temp.png\")).getImage(),\n" +
			"\t\t\t\t\t\t\t\t\t\t(new ImageIcon(\"images/enemy/movement/civ/temp.png\")).getImage()\n" +
			"\t\t\t\t\t\t\t\t\t\t};\n" +
			"\t\t\t\n" +
			"\t\t\t\n" +
			"\t\t\t//---------------- for movement images---------------------------------------\n" +
			"\t\t\tif(meleeImages != null){\n" +
			"\t\t\t\tmeleeMovement = new BufferedImage[8][meleeImages.length];\n" +
			"\t\t\t\tfor(int i=0; i<meleeImages.length; i++){\n" +
			"\t\t\t\t\tmeleeMovement[0][i] = getRotatedBI(meleeImages, i, scale, null, 0);\n" +
			"\t\t\t\t\tmeleeMovement[1][i] = getRotatedBI(meleeImages, i, scale, null, 45);\n" +
			"\t\t\t\t\tmeleeMovement[2][i] = getRotatedBI(meleeImages, i, scale, null, 90);\n" +
			"\t\t\t\t\tmeleeMovement[3][i] = getRotatedBI(meleeImages, i, scale, null, 135);\n" +
			"\t\t\t\t\tmeleeMovement[4][i] = getRotatedBI(meleeImages, i, scale, null, 180);\n" +
			"\t\t\t\t\tmeleeMovement[5][i] = getRotatedBI(meleeImages, i, scale, null, 225);\n" +
			"\t\t\t\t\tmeleeMovement[6][i] = getRotatedBI(meleeImages, i, scale, null, 270);\n" +
			"\t\t\t\t\tmeleeMovement[7][i] = getRotatedBI(meleeImages, i, scale, null, 315);\n" +
			"\t\t\t\t}\n" +
			"\t\t\t}\n" +
			"\t\t\t\n" +
			"\t\t\tif(rangedImages != null){\n" +
			"\t\t\t\trangedMovement = new BufferedImage[8][rangedImages.length];\n" +
			"\t\t\t\tfor(int i=0; i<rangedImages.length; i++){\n" +
			"\t\t\t\t\trangedMovement[0][i] = getRotatedBI(rangedImages, i, scale, null, 0);\n" +
			"\t\t\t\t\trangedMovement[1][i] = getRotatedBI(rangedImages, i, scale, null, 45);\n" +
			"\t\t\t\t\trangedMovement[2][i] = getRotatedBI(rangedImages, i, scale, null, 90);\n" +
			"\t\t\t\t\trangedMovement[3][i] = getRotatedBI(rangedImages, i, scale, null, 135);\n" +
			"\t\t\t\t\trangedMovement[4][i] = getRotatedBI(rangedImages, i, scale, null, 180);\n" +
			"\t\t\t\t\trangedMovement[5][i] = getRotatedBI(rangedImages, i, scale, null, 225);\n" +
			"\t\t\t\t\trangedMovement[6][i] = getRotatedBI(rangedImages, i, scale, null, 270);\n" +
			"\t\t\t\t\trangedMovement[7][i] = getRotatedBI(rangedImages, i, scale, null, 315);\n" +
			"\t\t\t\t}\n" +
			"\t\t\t}\n" +
			"\t\t\t\n" +
			"\t\t\tif(vendorUnitImages != null){\n" +
			"\t\t\t\tvendorUnitMovement = new BufferedImage[8][vendorUnitImages.length];\n" +
			"\t\t\t\tfor(int i=0; i<vendorUnitImages.length; i++){\n" +
			"\t\t\t\t\tvendorUnitMovement[0][i] = getRotatedBI(vendorUnitImages, i, scale, null, 0);\n" +
			"\t\t\t\t\tvendorUnitMovement[1][i] = getRotatedBI(vendorUnitImages, i, scale, null, 45);\n" +
			"\t\t\t\t\tvendorUnitMovement[2][i] = getRotatedBI(vendorUnitImages, i, scale, null, 90);\n" +
			"\t\t\t\t\tvendorUnitMovement[3][i] = getRotatedBI(vendorUnitImages, i, scale, null, 135);\n" +
			"\t\t\t\t\tvendorUnitMovement[4][i] = getRotatedBI(vendorUnitImages, i, scale, null, 180);\n" +
			"\t\t\t\t\tvendorUnitMovement[5][i] = getRotatedBI(vendorUnitImages, i, scale, null, 225);\n" +
			"\t\t\t\t\tvendorUnitMovement[6][i] = getRotatedBI(vendorUnitImages, i, scale, null, 270);\n" +
			"\t\t\t\t\tvendorUnitMovement[7][i] = getRotatedBI(vendorUnitImages, i, scale, null, 315);\n" +
			"\t\t\t\t}\n" +
			"\t\t\t}\n" +
			"\t\t\t\n" +
			"\t\t\tif(questUnitImages != null){\n" +
			"\t\t\t\tquestUnitMovement = new BufferedImage[8][questUnitImages.length];\n" +
			"\t\t\t\tfor(int i=0; i<questUnitImages.length; i++){\n" +
			"\t\t\t\t\tquestUnitMovement[0][i] = getRotatedBI(questUnitImages, i, scale, null, 0);\n" +
			"\t\t\t\t\tquestUnitMovement[1][i] = getRotatedBI(questUnitImages, i, scale, null, 45);\n" +
			"\t\t\t\t\tquestUnitMovement[2][i] = getRotatedBI(questUnitImages, i, scale, null, 90);\n" +
			"\t\t\t\t\tquestUnitMovement[3][i] = getRotatedBI(questUnitImages, i, scale, null, 135);\n" +
			"\t\t\t\t\tquestUnitMovement[4][i] = getRotatedBI(questUnitImages, i, scale, null, 180);\n" +
			"\t\t\t\t\tquestUnitMovement[5][i] = getRotatedBI(questUnitImages, i, scale, null, 225);\n" +
			"\t\t\t\t\tquestUnitMovement[6][i] = getRotatedBI(questUnitImages, i, scale, null, 270);\n" +
			"\t\t\t\t\tquestUnitMovement[7][i] = getRotatedBI(questUnitImages, i, scale, null, 315);\n" +
			"\t\t\t\t}\n" +
			"\t\t\t}\n" +
			"\t\t\t\n" +
			"\t\t\tif(civImages != null){\n" +
			"\t\t\t\tcivMovement = new BufferedImage[8][civImages.length];\n" +
			"\t\t\t\tfor(int i=0; i<civImages.length; i++){\n" +
			"\t\t\t\t\tcivMovement[0][i] = getRotatedBI(civImages, i, scale, null, 0);\n" +
			"\t\t\t\t\tcivMovement[1][i] = getRotatedBI(civImages, i, scale, null, 45);\n" +
			"\t\t\t\t\tcivMovement[2][i] = getRotatedBI(civImages, i, scale, null, 90);\n" +
			"\t\t\t\t\tcivMovement[3][i] = getRotatedBI(civImages, i, scale, null, 135);\n" +
			"\t\t\t\t\tcivMovement[4][i] = getRotatedBI(civImages, i, scale, null, 180);\n" +
			"\t\t\t\t\tcivMovement[5][i] = getRotatedBI(civImages, i, scale, null, 225);\n" +
			"\t\t\t\t\tcivMovement[6][i] = getRotatedBI(civImages, i, scale, null, 270);\n" +
			"\t\t\t\t\tcivMovement[7][i] = getRotatedBI(civImages, i, scale, null, 315);\n" +
			"\t\t\t\t}\n" +
			"\t\t\t}\n" +
			"\t\t}\n" +
			"\t\tprivate BufferedImage getRotatedBI(Image[] images, int index, double scale, JPanel panel, double angle){\n" +
			"\t\t\tBufferedImage newImage = new BufferedImage((int)(scale * 2), (int)(scale * 2), BufferedImage.TYPE_INT_ARGB);\n" +
			"\t\t\tGraphics2D g2d = newImage.createGraphics();\n" +
			"\t\t\tAffineTransform at = AffineTransform.getRotateInstance(Math.toRadians(angle + 90), (scale), (scale));\n" +
			"\t\t\tAffineTransformOp op = new AffineTransformOp(at, AffineTransformOp.TYPE_BILINEAR);\n" +
			"\n" +
			"\t\t\tg2d.drawImage(images[index], (int) (scale/2), (int) (scale/2), (int) (scale * 1), (int) (scale * 1), panel);\n" +
			"\n" +
			"\t\t\tBufferedImage rotatedImage = new BufferedImage((int)(scale * 2), (int)(scale * 2), BufferedImage.TYPE_INT_ARGB);\n" +
			"\t\t\tGraphics2D g = rotatedImage.createGraphics();\n" +
			"\t\t\tg.drawImage(op.filter(newImage, null), 0, 0, panel);\n" +
			"\n" +
			"\t\t\treturn rotatedImage;\n" +
			"\t\t}\n" +
			"\t\t//</editor-fold>\n" +
			"\t\t\n" +
			"\t\t//extra code commented out\n" +
			"\t\t//<editor-fold defaultstate=\"collapsed\">\n" +
			"//\t\tpublic BufferedImage[][] setBufferedImage(Image[] images, JPanel panel, float scale){\n" +
			"//\t\t\tif(images == null){\n" +
			"//\t\t\t\treturn null;\n" +
			"//\t\t\t}\n" +
			"//\t\t\t\n" +
			"//\t\t\tBufferedImage[][] theBImages = new BufferedImage[8][images.length];;\n" +
			"//\t\t\t\n" +
			"//\t\t\tfor(int i=0; i<images.length; i++){\n" +
			"//\t\t\t\ttheBImages[0][i] = getRotatedBI(images, i, scale, null, 0);\n" +
			"//\t\t\t\ttheBImages[1][i] = getRotatedBI(images, i, scale, null, 45);\n" +
			"//\t\t\t\ttheBImages[2][i] = getRotatedBI(images, i, scale, null, 90);\n" +
			"//\t\t\t\ttheBImages[3][i] = getRotatedBI(images, i, scale, null, 135);\n" +
			"//\t\t\t\ttheBImages[4][i] = getRotatedBI(images, i, scale, null, 180);\n" +
			"//\t\t\t\ttheBImages[5][i] = getRotatedBI(images, i, scale, null, 225);\n" +
			"//\t\t\t\ttheBImages[6][i] = getRotatedBI(images, i, scale, null, 270);\n" +
			"//\t\t\t\ttheBImages[7][i] = getRotatedBI(images, i, scale, null, 315);\n" +
			"//\t\t\t}\n" +
			"//\t\t\t\n" +
			"//\t\t\treturn theBImages;\n" +
			"//\t\t}\n" +
			"//\t\tprivate BufferedImage getRotatedBI(Image[] images, int index, double scale, JPanel panel, double angle){\n" +
			"//\t\t\tBufferedImage newImage = new BufferedImage((int)(scale * 2), (int)(scale * 2), BufferedImage.TYPE_INT_ARGB);\n" +
			"//\t\t\tGraphics2D g2d = newImage.createGraphics();\n" +
			"//\t\t\tAffineTransform at = AffineTransform.getRotateInstance(Math.toRadians(angle + 90), (scale), (scale));\n" +
			"//\t\t\tAffineTransformOp op = new AffineTransformOp(at, AffineTransformOp.TYPE_BILINEAR);\n" +
			"//\n" +
			"//\t\t\tg2d.drawImage(images[index], (int) (scale/2), (int) (scale/2), (int) (scale * 1), (int) (scale * 1), panel);\n" +
			"//\n" +
			"//\t\t\tBufferedImage rotatedImage = new BufferedImage((int)(scale * 2), (int)(scale * 2), BufferedImage.TYPE_INT_ARGB);\n" +
			"//\t\t\tGraphics2D g = rotatedImage.createGraphics();\n" +
			"//\t\t\tg.drawImage(op.filter(newImage, null), 0, 0, panel);\n" +
			"//\n" +
			"//\t\t\treturn rotatedImage;\n" +
			"//\t\t}\n" +
			"\n" +
			"//\t\tprivate BufferedImage getRotatedBI(Image[] images, int index, double scale, JPanel panel, double angle){\n" +
			"//\t\t\tBufferedImage newImage = new BufferedImage((int)(scale * 2), (int)(scale * 2), BufferedImage.TYPE_INT_ARGB);\n" +
			"//\t\t\tGraphics2D g2d = newImage.createGraphics();\n" +
			"//\t\t\tAffineTransform at = AffineTransform.getRotateInstance((angle), (scale), (scale));\n" +
			"//\t\t\tAffineTransformOp op = new AffineTransformOp(at, AffineTransformOp.TYPE_BILINEAR);\n" +
			"//\n" +
			"//\t\t\tg2d.drawImage(images[index], (int) (scale/2), (int) (scale/2), (int) (scale * 1), (int) (scale * 1), panel);\n" +
			"//\n" +
			"//\t\t\tBufferedImage rotatedImage = new BufferedImage((int)(scale * 2), (int)(scale * 2), BufferedImage.TYPE_INT_ARGB);\n" +
			"//\t\t\tGraphics2D g = rotatedImage.createGraphics();\n" +
			"//\t\t\tg.drawImage(op.filter(newImage, null), 0, 0, panel);\n" +
			"//\n" +
			"//\t\t\treturn rotatedImage;\n" +
			"//\t\t}\n" +
			"//\n" +
			"//\t\tpublic BufferedImage[] getImages(){\n" +
			"//\t\t\treturn image;\n" +
			"//\t\t}\n" +
			"\t\t//</editor-fold>\n" +
			"\t//</editor-fold>\n" +
			"\t\n" +
			"\tprivate void updateGame(){\n" +
			"\t\t//WRITE(\"main thread running\");\n" +
			"\t\tif(!paused && !displayingMap){\n" +
			"\t\t\tcurrentUpdateTime = System.nanoTime();\n" +
			"\t\t\tupdates++;\n" +
			"\t\t\tif((currentUpdateTime - lastUPSUpdate) >= 1000000000){\n" +
			"\t\t\t\tups = updates;\t\n" +
			"\t\t\t\tlastUPSUpdate = currentUpdateTime;\n" +
			"\t\t\t\tupdates = 0;\n" +
			"\t\t\t}\n" +
			"\t\t\n" +
			"\t\t\tif(!gameOver){\n" +
			"\t\t\t\tplayer.move(map);\n" +
			"\t\t\t\tquests.checkTravelQuests(player);\n" +
			"\t\t\t\tplayer.regenStats();\n" +
			"\t\t\t\tplayer.chargeAttack(time);\n" +
			"\t\t\t\t//tryRepeatingMouse();\n" +
			"\t\t\t\tif(player.isDead()){\n" +
			"\t\t\t\t\tgameOver = true;\n" +
			"\t\t\t\t}\n" +
			"\t\t\t}\n" +
			"\t\t\t\n" +
			"\t\t\tcheckEntityPickup();\n" +
			"\t\t\tmoveProjectiles();\n" +
			"\t\t\tcheckRemoveProjectiles();\n" +
			"\n" +
			"\t\t\tcheckEffectDespawn();\n" +
			"\t\t\tcheckEntityDespawn();\n" +
			"\t\t}\n" +
			"\t\tif(closeDown){\n" +
			"\t\t\tupdate.stop();\n" +
			"\t\t}\n" +
			"\t}\n" +
			"\t\n" +
			"\tprivate void respawnPlayer(){\n" +
			"\t\tif(gameOver){\n" +
			"\t\t\tplayer.respawn();\n" +
			"\t\t\tgameOver = false;\n" +
			"\t\t}\n" +
			"\t}\n" +
			"\t\n" +
			"\tArrayList<Projectile> enemyProjectiles;\n" +
			"\tclass EnemyManager implements Runnable, ActionListener{\n" +
			"\t\tTimer enemyTimer;\n" +
			"\t\tJPanel panel;\n" +
			"\t\t\n" +
			"\t\tpublic EnemyManager(JPanel panel){\n" +
			"\t\t\tenemyTimer = new Timer(17, this);\n" +
			"\t\t\tthis.panel = panel;\n" +
			"\t\t}\n" +
			"\t\t\n" +
			"\t\t@Override\n" +
			"\t\tpublic void run() {\n" +
			"\t\t\tenemyTimer.start();\n" +
			"\t\t}\n" +
			"\n" +
			"\t\t@Override\n" +
			"\t\tpublic void actionPerformed(ActionEvent e) {\n" +
			"\t\t\tcurrentEUpdateTime = System.nanoTime();\n" +
			"\t\t\teupdates++;\n" +
			"\t\t\tif((currentEUpdateTime - lastEPSUpdate) >= 1000000000){\n" +
			"\t\t\t\teps = eupdates;\t\n" +
			"\t\t\t\tlastEPSUpdate = currentEUpdateTime;\n" +
			"\t\t\t\teupdates = 0;\n" +
			"\t\t\t}\n" +
			"\t\t\t//WRITE(\"enemy thread running\");\n" +
			"\t\t\tif(!paused){\n" +
			"\t\t\t\tcheckEnemySpawning();\n" +
			"\t\t\t\tmoveEnemies();\n" +
			"\t\t\t\tcheckEnemyAttackAnimations();\n" +
			"\t\t\t\tmoveEnemyProjectiles();\n" +
			"\t\t\t\tcheckEnemyProjectileCollisions();\n" +
			"\t\t\t}\n" +
			"\t\t\tif(closeDown){\n" +
			"\t\t\t\tenemyTimer.stop();\n" +
			"\t\t\t}\n" +
			"\t\t}\n" +
			"\t\t\n" +
			"\t\t//for enemies\n" +
			"\t\t//<editor-fold defaultstate=\"collapsed\">\n" +
			"\t//ArrayList<Unit> enemies;\n" +
			"\t\n" +
			"\t\n" +
			"\tint spawnCheckRange = 5;\n" +
			"\tprivate void checkEnemySpawning(){\n" +
			"\t\tfor(int i = -spawnCheckRange; i <= spawnCheckRange; i++){\n" +
			"\t\t\tfor(int j = -spawnCheckRange; j <= spawnCheckRange; j++){\n" +
			"\t\t\t\tChunk current = map.getFromCoord(getMapChunkX() + i, getMapChunkY() + j);\n" +
			"\t\t\t\tif(current != null){\n" +
			"\t\t\t\t\tcurrent.checkSpawn(time, scale, panel);\n" +
			"\t\t\t\t}\n" +
			"\t\t\t}\n" +
			"\t\t}\n" +
			"\t}\n" +
			"\t\n" +
			"//\tprivate void prepareEnemies(){\n" +
			"//\t\tenemies = new ArrayList<>();\n" +
			"//\t\t\n" +
			"//\t\tUnit newRangedEnemy = new Unit(10, -5, 1, 1, ENEMY_RANGED, 10, 6, 20, 2000, 50);\n" +
			"//\t\tnewRangedEnemy.setSpeed(scale / 10);\n" +
			"//\t\tenemies.add(newRangedEnemy);\n" +
			"//\t\t\n" +
			"//\t\tUnit newMeleeEnemy = new Unit(3, -15, 1, 1, ENEMY_MELEE, 5, .5f, 2, 100f, 100);\n" +
			"//\t\tnewMeleeEnemy.setSpeed(scale / 10);\n" +
			"//\t\tenemies.add(newMeleeEnemy);\n" +
			"//\t}\n" +
			"\t\n" +
			"\tprivate void moveEnemyProjectiles(){\n" +
			"\t\tfor(int i=0; i<enemyProjectiles.size(); i++){\n" +
			"\t\t\tenemyProjectiles.get(i).move();\n" +
			"\t\t}\n" +
			"\t}\n" +
			"\t\n" +
			"\tprivate void checkEnemyProjectileCollisions(){\n" +
			"\t\tfor(int i=0; i<enemyProjectiles.size(); i++){\n" +
			"\t\t\tProjectile currentProjectile = enemyProjectiles.get(i);\n" +
			"\t\t\tif(currentProjectile.intersects(map, entities, eid, time)){\n" +
			"\t\t\t\tenemyProjectiles.remove(i);\n" +
			"\t\t\t}\n" +
			"\t\t\telse if(time >= currentProjectile.getSpawnTime() + currentProjectile.getDelay()){\n" +
			"\t\t\t\tenemyProjectiles.remove(i);\n" +
			"\t\t\t}\n" +
			"\t\t\telse if(fullIntersects(player.getX() + .2, player.getY() + .2, .6, .6, \n" +
			"\t\t\t\t\tcurrentProjectile.getX() - currentProjectile.getWidth()/2, currentProjectile.getY() - currentProjectile.getHeight()/2, currentProjectile.getWidth(), currentProjectile.getHeight()) ){\n" +
			"\t\t\t\tint dead = player.damage(currentProjectile.getAttackBonus());\n" +
			"\t\t\t\tenemyProjectiles.remove(i);\n" +
			"\t\t\t\teffects.add(new Effect(player.getX() + .5, player.getY() + .5, .75, .75, GType.EFFECT_BLOOD, time));\n" +
			"\t\t\t\tif(dead == 1){\n" +
			"\t\t\t\t\tgameOver = true;\n" +
			"\t\t\t\t}\n" +
			"\t\t\t}\n" +
			"\t\t}\n" +
			"\t}\n" +
			"\t\n" +
			"\tprivate void moveEnemies(){\n" +
			"\t\tfor(int i = -chunkWidthDistance(); i <= chunkWidthDistance(); i++){\n" +
			"\t\t\tfor(int j = -chunkHeightDistance(); j <= chunkHeightDistance(); j++){\n" +
			"\t\t\t\tChunk current = map.getFromCoord(getMapChunkX() + i, getMapChunkY() + j);\n" +
			"\t\t\t\tif(current != null){\n" +
			"//\t\t\t\t\tif(!current.initialized){\n" +
			"//\t\t\t\t\t\tcurrent.initialSpawn(scale, panel);\n" +
			"//\t\t\t\t\t}\n" +
			"\t\t\t\t\tcurrent.moveEnemies(player, time, enemyProjectiles, map, scale, panel, effects);\n" +
			"\t\t\t\t}\n" +
			"\t\t\t}\n" +
			"\t\t}\n" +
			"\t}\n" +
			"\tprivate void checkEnemyAttackAnimations(){\n" +
			"\t\tfor(int i = -chunkWidthDistance(); i <= chunkWidthDistance(); i++){\n" +
			"\t\t\tfor(int j = -chunkHeightDistance(); j <= chunkHeightDistance(); j++){\n" +
			"\t\t\t\tChunk current = map.getFromCoord(getMapChunkX() + i, getMapChunkY() + j);\n" +
			"\t\t\t\tif(current != null){\n" +
			"\t\t\t\t\tcurrent.checkEnemyAttackAnimations();\n" +
			"\t\t\t\t}\n" +
			"\t\t\t}\n" +
			"\t\t}\n" +
			"\t}\n" +
			"\t//</editor-fold>\n" +
			"\t}\n" +
			"\n" +
			"\tclass ChunkManager implements Runnable, ActionListener{\n" +
			"\t\tTimer chunkTimer;\n" +
			"\t\tpublic ChunkManager(){\n" +
			"\t\t\tchunkTimer = new Timer(100, this);\n" +
			"\t\t}\n" +
			"\t\t\n" +
			"\t\t@Override\n" +
			"\t\tpublic void run() {\n" +
			"\t\t\tchunkTimer.start();\n" +
			"\t\t}\n" +
			"\n" +
			"\t\t@Override\n" +
			"\t\tpublic void actionPerformed(ActionEvent e) {\n" +
			"\t\t\tcurrentCUpdateTime = System.nanoTime();\n" +
			"\t\t\tcupdates++;\n" +
			"\t\t\tif((currentCUpdateTime - lastCPSUpdate) >= 1000000000){\n" +
			"\t\t\t\tcps = cupdates;\t\n" +
			"\t\t\t\tlastCPSUpdate = currentCUpdateTime;\n" +
			"\t\t\t\tcupdates = 0;\n" +
			"\t\t\t}\n" +
			"\t\t\t//WRITE(\"chunk manager running\");\n" +
			"\t\t\t//checkChunkGenerating();\n" +
			"\t\t\totherCheckChunkGeneration();\n" +
			"\t\t\tregenTileHealth();\n" +
			"\t\t\tif(closeDown){\n" +
			"\t\t\t\tchunkTimer.stop();\n" +
			"\t\t\t}\n" +
			"\t\t}\n" +
			"\t\t\n" +
			"\t\tprivate void regenTileHealth(){\n" +
			"\t\t\tfor(int i = -chunkWidthDistance(); i <= chunkWidthDistance(); i++){\n" +
			"\t\t\t\tfor(int j = -chunkHeightDistance(); j <= chunkHeightDistance(); j++){\n" +
			"\t\t\t\t\tChunk current = map.getFromCoord(getMapChunkX() + i, getMapChunkY() + j);\n" +
			"\t\t\t\t\tif(current != null){\n" +
			"\t\t\t\t\t\tcurrent.regenBlocks();\n" +
			"\t\t\t\t\t}\n" +
			"\t\t\t\t}\n" +
			"\t\t\t}\n" +
			"\t\t}\n" +
			"\t\t\n" +
			"\t\tprivate void otherCheckChunkGeneration(){\n" +
			"\t\t\tint mapX = map.getIndexX(getMapChunkX()), mapY = map.getIndexY(getMapChunkY());\n" +
			"\t\t\t//System.out.println(\"X: \" + mapX + \" | \");\n" +
			"\n" +
			"\t\t\tboolean skip = false;\n" +
			"\t\t\t\n" +
			"\t\t\tfor(int r = 0; r <= GType.getGenerationRange(); r++)\n" +
			"\t\t\t{\n" +
			"\t\t\t\tfor(int j = -r; j <= r; j++)\n" +
			"\t\t\t\t{\n" +
			"\t\t\t\t\tfor(int i = -r; i <= r; i++)\n" +
			"\t\t\t\t\t{\n" +
			"\t\t\t\t\t\tif(Math.abs(i) + Math.abs(j) == r)\n" +
			"\t\t\t\t\t\t{\n" +
			"\t\t\t\t\t\t\tif(isOnMap(mapX + i, mapY + j))\n" +
			"\t\t\t\t\t\t\t{\n" +
			"\t\t\t\t\t\t\t\tif(map.toArray()[mapY + j][mapX + i] == null)\n" +
			"\t\t\t\t\t\t\t\t{\n" +
			"\t\t\t\t\t\t\t\t\tChunk newChunk = new Chunk();\n" +
			"\t\t\t\t\t\t\t\t\tnewChunk.generateChunk(map.toArray(), biomeList, towns, roadManager, mapX + i, mapY + j, map.getCoordX(mapX + i), map.getCoordY(mapY + j), GAME_CONNECTEDNESS);\n" +
			"\t\t\t\t\t\t\t\t\tnewChunk.completeSetup(scale, time);\n" +
			"\t\t\t\t\t\t\t\t\tnewChunk.initialSpawn(scale, time);\n" +
			"\t\t\t\t\t\t\t\t\tmap.set(newChunk, map.getCoordX(mapX + i), map.getCoordY(mapY + j));\n" +
			"\t\t\t\t\t\t\t\t\tskip = true;\n" +
			"\t\t\t\t\t\t\t\t\tbreak;\n" +
			"\t\t\t\t\t\t\t\t}\n" +
			"\t\t\t\t\t\t\t}\n" +
			"\t\t\t\t\t\t\telse\n" +
			"\t\t\t\t\t\t\t{\n" +
			"\t\t\t\t\t\t\t\tChunk newChunk = new Chunk();\n" +
			"\n" +
			"\t\t\t\t\t\t\t\tmap.tryGrow(map.getCoordX(mapX + i), map.getCoordY(mapY + j));\n" +
			"\t\t\t\t\t\t\t\tmapX = map.getIndexX(getMapChunkX());\n" +
			"\t\t\t\t\t\t\t\tmapY = map.getIndexY(getMapChunkY());\n" +
			"\n" +
			"\t\t\t\t\t\t\t\tnewChunk.generateChunk(map.toArray(), biomeList, towns, roadManager, mapX + i, mapY + j, map.getCoordX(mapX + i), map.getCoordY(mapY + j), GAME_CONNECTEDNESS);\n" +
			"\t\t\t\t\t\t\t\tnewChunk.completeSetup(scale, time);\n" +
			"\t\t\t\t\t\t\t\tnewChunk.initialSpawn(scale, time);\n" +
			"\t\t\t\t\t\t\t\tmap.set(newChunk, map.getCoordX(mapX + i), map.getCoordY(mapY + j));\n" +
			"\t\t\t\t\t\t\t\tskip = true;\n" +
			"\t\t\t\t\t\t\t\tbreak;\n" +
			"\t\t\t\t\t\t\t}\n" +
			"\t\t\t\t\t\t}\n" +
			"\t\t\t\t\t}\n" +
			"\t\t\t\t\tif(skip){\n" +
			"\t\t\t\t\t\tbreak;\n" +
			"\t\t\t\t\t}\n" +
			"\t\t\t\t}\n" +
			"\t\t\t\tif(skip){\n" +
			"\t\t\t\t\tbreak;\n" +
			"\t\t\t\t}\n" +
			"\t\t\t}\n" +
			"\t\t}\n" +
			"\t\t\n" +
			"\t\tpublic boolean isOnMap(int tx, int ty)\n" +
			"\t\t{\n" +
			"\t\t\tboolean onMap;\n" +
			"\t\t\tif(tx < 0)\n" +
			"\t\t\t{\n" +
			"\t\t\t\tonMap = false;\n" +
			"\t\t\t}\n" +
			"\t\t\telse if(tx >= map.toArray()[0].length)\n" +
			"\t\t\t{\n" +
			"\t\t\t\tonMap = false;\n" +
			"\t\t\t}\n" +
			"\t\t\telse if(ty < 0)\n" +
			"\t\t\t{\n" +
			"\t\t\t\tonMap = false;\n" +
			"\t\t\t}\n" +
			"\t\t\telse if(ty >= map.toArray().length)\n" +
			"\t\t\t{\n" +
			"\t\t\t\tonMap = false;\n" +
			"\t\t\t}\n" +
			"\t\t\telse\n" +
			"\t\t\t{\n" +
			"\t\t\t\tonMap = true;\n" +
			"\t\t\t}\n" +
			"\t\t\treturn onMap;\n" +
			"\t\t}\n" +
			"\t}\n" +
			"\t//for effects\n" +
			"\t//<editor-fold defaultstate=\"collapsed\">\n" +
			"\tArrayList<Effect> effects;\n" +
			"\tprivate void setupEffects(){\n" +
			"\t\teffects = new ArrayList<>();\n" +
			"\t}\n" +
			"\t\n" +
			"\tprivate void checkEffectDespawn(){\n" +
			"\t\tfor(int i=0; i<effects.size(); i++){\n" +
			"\t\t\tboolean done = effects.get(i).checkDespawn(time);\n" +
			"\t\t\tif(done){\n" +
			"\t\t\t\teffects.remove(i);\n" +
			"\t\t\t}\n" +
			"\t\t}\n" +
			"\t}\n" +
			"\t\n" +
			"\tvoid drawEffects(Graphics2D g){\n" +
			"\t\tfor(int i=0; i<effects.size(); i++){\n" +
			"\t\t\teffects.get(i).drawEffect(g, getDrawingOffsetX(scale), getDrawingOffsetY(scale), scale);\n" +
			"\t\t}\n" +
			"\t}\n" +
			"\t//</editor-fold>\n" +
			"\t\n" +
			"\t//for the abilities\n" +
			"\t//<editor-fold defaultstate=\"collapsed\">\n" +
			"//\tprivate void drawAbilityIcons(Graphics2D g){\n" +
			"////\t\tg.setColor(Color.white);\n" +
			"////\t\t\n" +
			"////\t\tg.fillRect(0, (int) (screenHeight - scale * 3), (int)(scale * 2), (int)(scale * 2));\n" +
			"////\t\tg.drawImage(arrowIcon, 0, (int) (screenHeight - scale * 3), (int)(scale * 2), (int)(scale * 2), this);\n" +
			"//\t\t\n" +
			"////\t\tg.fillRect((int) (screenWidth - (int)(scale * 2)), (int) (screenHeight - scale * 3), (int)(scale * 2), (int)(scale * 2));\n" +
			"//\t\t\n" +
			"////\t\tg.fillRect((int) (scale * 2), (int) (screenHeight - scale * 3), (int)(scale * 2), (int)(scale * 2));\n" +
			"////\t\tg.drawImage(dashIcon, (int) (scale * 2), (int) (screenHeight - scale * 3), (int)(scale * 2), (int)(scale * 2), this);\n" +
			"//\t\t\n" +
			"////\t\tg.setColor(new Color(0, 0, 0, 50));\n" +
			"////\t\tg.fillRect(0, (int) (screenHeight - scale * 3), (int)(scale * 2), (int) ((scale * 2) * player.getCDPercent1(time)) );\n" +
			"////\t\tg.fillRect((int) (scale * 2), (int) (screenHeight - scale * 3), (int)(scale * 2), (int) ((scale * 2) * player.getCDPercent2(time)) );\n" +
			"//\t}\n" +
			"\t//</editor-fold>\n" +
			"\t\n" +
			"\t//for the level\n" +
			"\t//<editor-fold defaultstate=\"collapsed\">\n" +
			"\tint levelBarWidth, levelBarHeight;\n" +
			"\tprivate void setupCharLevel(){\n" +
			"\t\tlevelBarWidth = (int) (screenWidth / 10);\n" +
			"\t\tlevelBarHeight = levelBarWidth / 5;\n" +
			"\t}\n" +
			"\t//</editor-fold>\n" +
			"\t\n" +
			"\t//for the inventory\n" +
			"\t//<editor-fold defaultstate=\"collapsed\">\n" +
			"//\tboolean drawInventory = true;\n" +
			"//\tint invWinWidth, invWinHeight, invX, invY, slotWidth, slotHeight, slotOffset;\n" +
			"//\tprivate void setupInventory(){\n" +
			"//\t\tinvWinWidth = (int) (screenWidth / 3);\n" +
			"//\t\tinvWinHeight = (int) (screenHeight / 2);\n" +
			"//\t\tinvX = (int) (screenWidth / 3);\n" +
			"//\t\tinvY = (int) (screenHeight / 4);\n" +
			"//\t\tslotWidth = invWinWidth / 4;\n" +
			"//\t\tslotHeight = invWinHeight / 4;\n" +
			"//\t\tslotOffset = slotWidth / 4;\n" +
			"//\t}\n" +
			"\t\n" +
			"//\tprivate void drawInventory(Graphics2D g){\n" +
			"//\t\tif(drawInventory){\n" +
			"//\t\t\tg.setColor(new Color(0, 0, 0, 100));\n" +
			"//\t\t\t//background\n" +
			"//\t\t\tg.fillRect(invX, invY, invWinWidth, invWinHeight);\n" +
			"//\t\t\t\n" +
			"//\t\t\t//item background and information\n" +
			"//\t\t\tg.setColor(new Color(50, 50, 50, 100));\n" +
			"//\t\t\tg.fillRect(invX + slotOffset, (int) (screenHeight/2 - slotHeight/2), slotWidth, slotHeight);\n" +
			"//\t\t\tg.fillRect(invX + invWinWidth - slotWidth - slotOffset, (int) (screenHeight/2 - slotHeight/2), slotWidth, slotHeight);\n" +
			"//\t\t\tg.fillRect(invX + invWinWidth/2 - slotWidth/2, (int) (screenHeight/2 - slotHeight/2), slotWidth, slotHeight);\n" +
			"//\t\t}\n" +
			"//\t}\n" +
			"\t//</editor-fold>\n" +
			"\n" +
			"\t//Methods for preparing the game\n" +
			"\t//<editor-fold defaultstate=\"collapsed\">\n" +
			"\tprivate void setupBiomes(){\n" +
			"\t\tbiomeList = new ArrayList<>();\n" +
			"\t\tFile biomeInfoFile = new File(\"settings/biome.info\");\n" +
			"\t\tif(!biomeInfoFile.exists()){\n" +
			"\t\t\ttry {\n" +
			"\t\t\t\tbiomeInfoFile.createNewFile();\n" +
			"\t\t\t} catch (IOException ex) {\n" +
			"\t\t\t\tWRITE(\"ERROR - Was not able to create the biome info file\");\n" +
			"\t\t\t}\n" +
			"\t\t}\n" +
			"\t\ttry {\n" +
			"\t\t\tBufferedReader br = new BufferedReader(new FileReader(biomeInfoFile));\n" +
			"\t\t\tString readLine = \"\";\n" +
			"\t\t\tint biomeIndex = 0;\n" +
			"\t\t\twhile((readLine = br.readLine()) != null){\n" +
			"\t\t\t\tif(!readLine.startsWith(\"//\")){\n" +
			"\t\t\t\t\treadLine = readLine.replace(\"\\t\", \"\");\n" +
			"\t\t\t\t\treadLine = readLine.replace(\" \", \"\");\n" +
			"\t\t\t\t\tWRITE(readLine);\n" +
			"\t\t\t\t\n" +
			"\t\t\t\t\tString[] splitReadLine = readLine.split(\":\");\n" +
			"\n" +
			"\t\t\t\t\tbiomeList.add(new Biome(splitReadLine[0]));\n" +
			"\t\t\t\t\tbiomeList.get(biomeIndex).setColor(Color.PINK);\n" +
			"\t\t\t\t\tbiomeList.get(biomeIndex).index = biomeIndex;\n" +
			"\t\t\t\t\tbiomeList.get(biomeIndex).setFloorIndex(Tools.Parse.getIntParse(splitReadLine[1]));\n" +
			"\t\t\t\t\tbiomeList.get(biomeIndex).setObjProb(Tools.Parse.getDoubleParse(splitReadLine[2]));\n" +
			"\t\t\t\t\tbiomeList.get(biomeIndex).setObjIndex(Tools.Parse.getIntParse(splitReadLine[3]));\n" +
			"\t\t\t\t\tbiomeList.get(biomeIndex).setWaterProbability(Tools.Parse.getDoubleParse(splitReadLine[4]));\n" +
			"\t\t\t\t\tbiomeList.get(biomeIndex).setWaterSize(Tools.Parse.getIntParse(splitReadLine[5]));\n" +
			"\t\t\t\t\tbiomeList.get(biomeIndex).setTreasureProb(Tools.Parse.getDoubleParse(splitReadLine[6]));\n" +
			"\n" +
			"\t\t\t\t\tbiomeIndex++;\n" +
			"\t\t\t\t}\n" +
			"\t\t\t}\n" +
			"\t\t} catch (FileNotFoundException ex) {\n" +
			"\t\t\tWRITE(\"ERROR - Was not able to create buffered reader for biome info file\");\n" +
			"\t\t} catch (IOException ex) {\n" +
			"\t\t\tWRITE(\"ERROR - was not able to read from the biome info file\");\n" +
			"\t\t}\n" +
			"\t}\n" +
			"\t\n" +
			"\tprivate void setupEvents(JFrame frame){\n" +
			"\t\tframe.addKeyListener(new KE());\n" +
			"\t\tframe.addMouseListener(new ME());\n" +
			"\t}\n" +
			"\t\n" +
			"\tprivate void setupGame(int width, int height) {\n" +
			"\t\tenemyThread = new Thread(new EnemyManager(this));\n" +
			"\t\tchunkThread = new Thread(new ChunkManager());\n" +
			"\t\tgraphicsThread = new Thread(new GraphicsEngine());\n" +
			"\t\t\n" +
			"\t\tsetupScale(width, height);\n" +
			"\t\tsetupTimer();\n" +
			"\t\t//setupPlayerSpeed();\n" +
			"\t\t//prepareEnemies();\n" +
			"\t\t//setupOffsets();\n" +
			"\t\t\n" +
			"\t\tupdate.start();\n" +
			"\t\tenemyThread.start();\n" +
			"\t\tchunkThread.start();\n" +
			"\t\tgraphicsThread.start();\n" +
			"\t}\n" +
			"\t\n" +
			"\tprivate void setupTimer(){\n" +
			"\t\tupdate = new Timer(17, this);\n" +
			"\t}\n" +
			"\t\n" +
			"\tprivate void setupScale(int width, int height){\n" +
			"\t\tscreenWidth = width;\n" +
			"\t\tSystem.out.println(\"the width is: \" + screenWidth);\n" +
			"\t\tscreenHeight = height;\n" +
			"\t\t//scale = screenWidth / 30;\n" +
			"\t\tchunkSize = CHUNK_SIZE;\n" +
			"\t}\n" +
			"\t//</editor-fold>\n" +
			"\n" +
			"\t//extra code for running game\n" +
			"\t//<editor-fold defaultstate=\"collapsed\">\n" +
			"\tdouble time;\n" +
			"\tlong lastUpdate, currentTime;\n" +
			"\t@Override\n" +
			"\tpublic void actionPerformed(ActionEvent e) {\n" +
			"\t\tcurrentTime = System.nanoTime();\n" +
			"\t\tif((currentTime - lastUpdate) >= 17000000){\n" +
			"\t\t\ttime += (currentTime - lastUpdate) / 1000000;\n" +
			"\t\t\tlastUpdate = currentTime;\n" +
			"\t\t}\n" +
			"\t\t\n" +
			"\t\tupdateGame();\n" +
			"\t}\n" +
			"\t//</editor-fold>\n" +
			"\t\n" +
			"\t//Drawing to the screen\n" +
			"\t//<editor-fold defaultstate=\"collapsed\">\n" +
			"\t@Override\n" +
			"\tpublic void paint(Graphics g){\n" +
			"\t\t\n" +
			"\t\tsuper.paint(g);\n" +
			"\t\tGraphics2D g2d = (Graphics2D) g;\n" +
			"\t\t\n" +
			"\t\tcurrentGraphicsTime = System.nanoTime();\n" +
			"\t\t\n" +
			"\t\t/*\n" +
			"\t\t\tSomething in drawing appears to be slowing down the game when you sit in a chunk for a while\n" +
			"\t\t\t\tseems to be something related to the enemies in the chunk - not size of the array list though as that stays at about 0/1/2 (small sizes)\n" +
			"\t\t\n" +
			"\t\t\tprobably the draw enemies\n" +
			"\t\t\n" +
			"\t\t\tnarrowed down\n" +
			"\t\t\t\teither drawmap, player, or interface...\n" +
			"\t\t\t\tmost likely something with the thread not running correctly...\n" +
			"\t\t\t\tAnother way to cap the drawing rate would be to add a queue int that gets added to every so many miliseconds \n" +
			"\t\t\t\t\ttwo threads or so would subtract one and update the screen\n" +
			"\t\t\n" +
			"\t\t\tadd back the drawing stuff when done narrowing it down\n" +
			"\t\t*/\n" +
			"\t\tif(displayingMap){\n" +
			"\t\t\tdrawMap(g2d);\n" +
			"\t\t\tdrawTownNames(g2d, (scale / mapScale));\n" +
			"\t\t\t//drawPlayer(g2d);\n" +
			"\t\t}\n" +
			"\t\telse{\n" +
			"\t\t\tdrawMain(g2d);\n" +
			"\t\t\n" +
			"\t\t\tdrawEnemies(g2d);\n" +
			"\t\t\tdrawPlayerProjectiles(g2d);\n" +
			"\t\t\tdrawEnemyProjectiles(g2d);\n" +
			"\n" +
			"\t\t\tdrawObjects(g2d);\n" +
			"\n" +
			"\t\t\tdrawPlayer(g2d);\n" +
			"\t\t\tdrawEffects(g2d);\n" +
			"\t\t\tdrawEntities(g2d);\n" +
			"\t\t\tdrawInterface(g2d);\n" +
			"\t\t\tdrawQuestData(g2d);\n" +
			"\t\t\t//drawMinimap(g2d);\n" +
			"\t\t\t\n" +
			"\t\t\tdrawTownNames(g2d, scale);\n" +
			"\t\t}\n" +
			"\t\t\n" +
			"\t\trepaints++;\n" +
			"\t\tif((currentGraphicsTime - lastFPSUpdate) >= 1000000000){\n" +
			"\t\t\tfps = repaints;\t\n" +
			"\t\t\tlastFPSUpdate = currentGraphicsTime;\n" +
			"\t\t\trepaints = 0;\n" +
			"\t\t}\n" +
			"\t}\n" +
			"\t\n" +
			"\tprivate void drawTownNames(Graphics2D g, float theScale){\n" +
			"\t\tfor(int i=0; i<towns.size(); i++){\n" +
			"\t\t\tTown t = towns.get(i);\n" +
			"\t\t\tfor(int m=0; m<t.chunks.length; m++){\n" +
			"\t\t\t\tPoint p = t.chunks[m];\n" +
			"\t\t\t\tif(p != null){\n" +
			"\t\t\t\t\tint x = (int)(getDrawingOffsetX(theScale) + ((p.x + .5) * CHUNK_SIZE * theScale));\n" +
			"\t\t\t\t\tint y = (int)(getDrawingOffsetY(theScale) + ((p.y + .5) * CHUNK_SIZE * theScale));\n" +
			"\n" +
			"\t\t\t\t\tg.setColor(Color.YELLOW);\n" +
			"\t\t\t\t\tdrawStringBounce(g, t.name, x, y);\n" +
			"\t\t\t\t}\n" +
			"\t\t\t}\n" +
			"\t\t}\n" +
			"\t}\n" +
			"\t\n" +
			"\tint mapScale = 16;\n" +
			"\tprivate void drawMap(Graphics2D g){\n" +
			"\t\tint x = getDrawingOffsetX(scale) + (int) Math.round((player.getIntX() * scale));\n" +
			"\t\tint y = getDrawingOffsetY(scale) + (int) Math.round((player.getIntY() * scale));\n" +
			"\t\t\n" +
			"\t\tfor(int j=-(STANDARD_SCALE_DRAW_HEIGHT * mapScale); j<=(STANDARD_SCALE_DRAW_HEIGHT * mapScale); j++){\n" +
			"\t\t\tfor(int i=-(STANDARD_SCALE_DRAW_WIDTH * mapScale); i<=(STANDARD_SCALE_DRAW_WIDTH * mapScale); i++){\n" +
			"\t\t\t\tTile currentTile = map.getTile(player.getIntX() + i, player.getIntY() + j);\n" +
			"\t\t\t\t\n" +
			"\t\t\t\tif(currentTile != null){\n" +
			"\t\t\t\t\tImage floorImage = floorImages.get(currentTile.floor);\n" +
			"\t\t\t\t\tint theScale = (int)(scale / mapScale);\n" +
			"\n" +
			"\t\t\t\t\tif(floorImage != null){\n" +
			"\t\t\t\t\t\t//WRITE(\"a non-null image is available for drawing to floor\");\n" +
			"\t\t\t\t\t\tint ow = floorImage.getWidth(null);\n" +
			"\t\t\t\t\t\tint oh = floorImage.getHeight(null);\n" +
			"\t\t\t\t\t\t\n" +
			"\t\t\t\t\t\tint woffset = (int)Math.round(theScale/2 - ((ow/IMAGE_TILE_SIZE)*theScale)/2);\n" +
			"\t\t\t\t\t\tint hoffset = (int)Math.round(theScale/2 - ((oh/IMAGE_TILE_SIZE)*theScale)/2);\n" +
			"\t\t\t\t\t\tint width = (int)Math.round((ow/IMAGE_TILE_SIZE) * theScale);\n" +
			"\t\t\t\t\t\tint height = (int)Math.round((oh/IMAGE_TILE_SIZE) * theScale);\n" +
			"\t\t\t\t\t\t\n" +
			"\t\t\t\t\t\t//g.drawImage(floorImage, x + i * theScale, y + j * theScale, theScale, theScale, null);\n" +
			"\t\t\t\t\t\tg.drawImage(floorImage, x + i * theScale + woffset, y + j * theScale + hoffset, width, height, null);\n" +
			"\t\t\t\t\t}\n" +
			"\t\t\t\t}\n" +
			"\t\t\t}\n" +
			"\t\t}\n" +
			"\t\t\n" +
			"\t\tfor(int j=-(STANDARD_SCALE_DRAW_HEIGHT * mapScale); j<=(STANDARD_SCALE_DRAW_HEIGHT * mapScale); j++){\n" +
			"\t\t\tfor(int i=-(STANDARD_SCALE_DRAW_WIDTH * mapScale); i<=(STANDARD_SCALE_DRAW_WIDTH * mapScale); i++){\n" +
			"\t\t\t\tdrawObject(g, x, y, i, j, (int)(scale / mapScale));\n" +
			"\t\t\t}\n" +
			"\t\t}\n" +
			"\t\t\n" +
			"\t\t//g.setColor(Color.RED);\n" +
			"\t\t//drawStringBounce(g, \"You are here\", (int)(screenWidth*), (int) (screenHeight/2));\n" +
			"\t\t//player.draw(g, screenWidth/2 - scale/2, screenHeight/2 - scale/2, scale, scale, scale, this, time, getMX(), getMY(), screenWidth, screenHeight);\n" +
			"\t\tplayer.drawMapStill(characterMovement, g, screenWidth/2 - scale/2, screenHeight/2 - scale/2, scale, this);\n" +
			"\t}\n" +
			"\t\n" +
			"\tprivate void drawPlayerProjectiles(Graphics2D g){\n" +
			"\t\tfor(int i=0; i<playerProjectiles.size(); i++){\n" +
			"\t\t\tplayerProjectiles.get(i).draw(axeImages,\tg, getDrawingOffsetX(scale), getDrawingOffsetY(scale), scale, (int)screenWidth/2, (int)screenHeight/2, time, this);\n" +
			"\t\t}\n" +
			"\t}\n" +
			"\t\n" +
			"\tprivate void drawEnemyProjectiles(Graphics2D g){\n" +
			"\t\tfor(int i=0; i<enemyProjectiles.size(); i++){\n" +
			"\t\t\tenemyProjectiles.get(i).draw(daggerImages,\tg, getDrawingOffsetX(scale), getDrawingOffsetY(scale), scale, (int)screenWidth/2, (int)screenHeight/2, time, this);\n" +
			"\t\t}\n" +
			"\t}\n" +
			"\t\n" +
			"\tprivate void drawEnemies(Graphics2D g){\n" +
			"\t\tfor(int j = -chunkHeightDistance(); j <= chunkHeightDistance(); j++){\n" +
			"\t\t\tfor(int i = -chunkWidthDistance(); i <= chunkWidthDistance(); i++){\n" +
			"\t\t\t\tChunk current = map.getFromCoord(getMapChunkX() + i, getMapChunkY() + j);\n" +
			"\t\t\t\tif(current != null){\n" +
			"\t\t\t\t\tcurrent.drawEnemies(meleeMovement, rangedMovement, vendorUnitMovement, questUnitMovement, civMovement, g, getDrawingOffsetX(scale), getDrawingOffsetY(scale), (int) scale, this, time);\n" +
			"\t\t\t\t}\n" +
			"\t\t\t}\n" +
			"\t\t}\n" +
			"\t}\n" +
			"\t\n" +
			"\t/*\n" +
			"\t\tthe drawing offset to place things correctly relative to player\n" +
			"\t\n" +
			"\t\tthe extra scale addition at the end is to correct for half of players size to center on middle of player\n" +
			"\t*/\n" +
			"\tprivate int getDrawingOffsetX(float scale){\n" +
			"\t\treturn (int) Math.round(screenWidth/2 - (player.getX() * scale) - scale/2);\n" +
			"\t}\n" +
			"\t\n" +
			"\tprivate int getDrawingOffsetY(float scale){\n" +
			"\t\treturn (int) (screenHeight/2 - (player.getY() * scale) - scale/2);\n" +
			"\t}\n" +
			"\t\n" +
			"\tprivate void drawInterface(Graphics2D g){\n" +
			"\t\t//drawInventory(g);\n" +
			"//\t\tdrawAbilityIcons(g);\n" +
			"\t\tdrawLevel(g);//draws after abilities because of special interface to cover up\n" +
			"\t\tdrawStats(g);\n" +
			"\t\tdrawInventory(g);\n" +
			"\t\t\n" +
			"\t\tif(showDeveloperStats){\n" +
			"\t\t\tdrawDeveloperData(g);\n" +
			"\t\t}\n" +
			"\t\t\n" +
			"\t\tif(gameOver){\n" +
			"\t\t\tg.setFont(defaultFont48);//font size 48\n" +
			"\t\t\tg.setColor(new Color(0, 0, 0, 150));\n" +
			"\t\t\tg.fillRect(0, (int)(screenHeight/2), (int)screenWidth, (int)(screenHeight/8));\n" +
			"\t\t\tg.setColor(new Color(200, 0, 0));\n" +
			"\t\t\tg.drawString(\"You died. Press space to respawn\", (float) (screenWidth*.3), (float) (screenHeight*.6));\n" +
			"\t\t}\n" +
			"\t}\n" +
			"\t\n" +
			"\tprivate void drawDeveloperData(Graphics2D g){\n" +
			"\t\tg.setFont(defaultFont24);//font size 24\n" +
			"\t\tg.setColor(Color.WHITE);\n" +
			"\t\tg.drawString(\"FPS: \" + fps + \" | UPS: \" + ups + \" | EPS: \" + eps + \" | CPS: \" + cps, screenWidth / 20, screenHeight / 10);\n" +
			"\t\tg.drawString(\"Game Time: \" + (\"(\" + getTimeMinutes() + \":\" + getTimeSeconds() + \":\" + getTimeMilli() + \")\"), screenWidth / 20, screenHeight / 10 + screenHeight / 30);\n" +
			"\t\tg.drawString(\"X: \" + player.getX() + \" | Y: \" + player.getY(), screenWidth / 20, screenHeight / 10 + 2 * screenHeight / 30);\n" +
			"\t\tg.drawString(\"lumber: \" + player.wood, screenWidth / 20, screenHeight / 10 + 3 * screenHeight / 30);\n" +
			"\t\tg.drawString(\"rock: \" + player.rock, screenWidth / 20, screenHeight / 10 + 4 * screenHeight / 30);\n" +
			"\t\tg.drawString(\"gold: \" + player.gold, screenWidth / 20, screenHeight / 10 + 5 * screenHeight / 30);\n" +
			"\t\tg.drawString(\"health pots: \" + player.healthPots, screenWidth / 20, screenHeight / 10 + 6 * screenHeight / 30);\n" +
			"\t}\n" +
			"\t\n" +
			"\t//for drawing quests\n" +
			"\t//<editor-fold defaultstate=\"collapsed\">\n" +
			"\tint questBumper = 0;\n" +
			"\tint questDrawingOffset = 0;\n" +
			"\tColor semiTransparent = new Color(0,0,0,150);\n" +
			"\tprivate void drawQuestData(Graphics2D g){\n" +
			"\t\tquestBumper = (int)(screenHeight/5.0);\n" +
			"\t\tg.setFont(defaultFont24);\n" +
			"\t\tg.setColor(Color.YELLOW);\n" +
			"\t\t\n" +
			"\t\tString totalQuestString = \"\";\n" +
			"\t\tfor(int i=questDrawingOffset; i<quests.getNumberOfCurrentQuests(); i++){\n" +
			"\t\t\tQuest quest = quests.getCurrentQuest(i);\n" +
			"\t\t\ttotalQuestString += quest.getTitle() + System.lineSeparator() + \n" +
			"\t\t\t\t\t\t\t\tquest.getDescription() + System.lineSeparator() +\n" +
			"\t\t\t\t\t\t\t\tquest.getAwardText()+ System.lineSeparator() +\n" +
			"\t\t\t\t\t\t\t\tquest.getProgress(player) + System.lineSeparator();\n" +
			"\t\t}\n" +
			"\t\t\n" +
			"\t\tint drawWidth = g.getFontMetrics(defaultFont24).charWidth('H') * 24;\n" +
			"\t\tint xLocation = (int) (screenWidth - drawWidth);\n" +
			"\t\tString[] allStrings = totalQuestString.split(System.lineSeparator());\n" +
			"\t\tint questWindowHeight = g.getFontMetrics(defaultFont24).getHeight()*4 + g.getFontMetrics(defaultFont48).getHeight();\n" +
			"\t\tdouble offset = 0;\n" +
			"\t\t\n" +
			"\t\tif(allStrings.length < 4){\n" +
			"\t\t\tallStrings = new String[0];\n" +
			"\t\t}\n" +
			"\t\t\n" +
			"\t\tfor(int i=0; i<allStrings.length; i += 4){\n" +
			"\t\t\tg.setColor(semiTransparent);\n" +
			"\t\t\tg.fillRect(xLocation - 12, questBumper + (int) (questDrawingOffset + offset), drawWidth + 12, questWindowHeight);\n" +
			"\t\t\t\n" +
			"\t\t\toffset += g.getFontMetrics(defaultFont24).getHeight()/2;\n" +
			"\t\t\t\n" +
			"\t\t\tg.setColor(Color.YELLOW);\n" +
			"\t\t\tg.setFont(defaultFont24);\n" +
			"\t\t\tg.drawString(\"------------------------\", xLocation, questBumper + (int) (questDrawingOffset + offset));\n" +
			"\t\t\toffset += g.getFontMetrics(defaultFont24).getHeight();\n" +
			"\t\t\t\n" +
			"\t\t\tg.setFont(defaultFont48);\n" +
			"\t\t\tg.drawString(\"\" + allStrings[i], xLocation, questBumper + (int) (questDrawingOffset + offset));\n" +
			"\t\t\toffset += g.getFontMetrics(defaultFont48).getHeight();\n" +
			"\t\t\t\n" +
			"\t\t\ttry{\n" +
			"\t\t\t\tg.setFont(defaultFont24);\n" +
			"\t\t\t\tg.drawString(\" \" + allStrings[i + 1], xLocation, questBumper + (int) (questDrawingOffset + offset));\n" +
			"\t\t\t\toffset += g.getFontMetrics(defaultFont24).getHeight();\n" +
			"\n" +
			"\t\t\t\tg.setFont(defaultFont24);\n" +
			"\t\t\t\tg.drawString(\" \" + allStrings[i + 2], xLocation, questBumper + (int) (questDrawingOffset + offset));\n" +
			"\t\t\t\toffset += g.getFontMetrics(defaultFont24).getHeight();\n" +
			"\n" +
			"\t\t\t\tg.setFont(defaultFont24);\n" +
			"\t\t\t\tg.drawString(\" \" + allStrings[i + 3], xLocation, questBumper + (int) (questDrawingOffset + offset));\n" +
			"\t\t\t\toffset += g.getFontMetrics(defaultFont24).getHeight();\n" +
			"\n" +
			"\t\t\t\toffset += g.getFontMetrics(defaultFont24).getHeight();\n" +
			"\t\t\t}catch(ArrayIndexOutOfBoundsException e){\n" +
			"\t\t\t\tWRITE(\"Tried to access an out of bounds index for quest details\");\n" +
			"\t\t\t}\n" +
			"\t\t}\n" +
			"\t\t\n" +
			"//\t\tg.drawString(totalQuestString, \n" +
			"//\t\t\t\tscreenWidth - g.getFontMetrics(defaultFont24).stringWidth(totalQuestString), \n" +
			"//\t\t\t\tscreenHeight/10);\n" +
			"\t}\n" +
			"\t//</editor-fold>\n" +
			"\t\n" +
			"\tdouble minimapWidth = .1;\n" +
			"\tdouble minimapHeight = .1;\n" +
			"\t//double mapWidth = .5;\n" +
			"\t//double mapHeight = .5;\n" +
			"//\tprivate void drawMinimap(Graphics2D g){\n" +
			"//\t\tg.setColor(Color.BLACK);\n" +
			"//\t\tg.fillRect((int) (screenWidth - minimapWidth), 0, (int)minimapWidth, (int)minimapHeight);\n" +
			"//\t}\n" +
			"\t\n" +
			"\tprivate void drawInventory(Graphics2D g){\n" +
			"\t\tg.setColor(new Color(0, 0, 0, 150));\n" +
			"\t\t\n" +
			"\t\t//for background\n" +
			"\t\tg.fillRect(\t(int)\t(screenWidth/2 - screenWidth/5), \n" +
			"\t\t\t\t\t0, \n" +
			"\t\t\t\t\t(int)\t(2*screenWidth/5), \n" +
			"\t\t\t\t\t(int)\t(screenWidth/20)\n" +
			"\t\t\t);\n" +
			"\t\t\n" +
			"\t\t//draw squares - temporarily until interface overlay is added\n" +
			"\t\tg.setColor(Color.GRAY);\n" +
			"\t\tfor(int i=0; i<8; i++){\n" +
			"\t\t\tg.drawRect((int)\t(screenWidth/2 - screenWidth/5 + i*(screenWidth/20)), \n" +
			"\t\t\t\t\t0, \n" +
			"\t\t\t\t\t(int)\t(screenWidth/20), \n" +
			"\t\t\t\t\t(int)\t(screenWidth/20)\n" +
			"\t\t\t);\n" +
			"\t\t\tg.drawString(\"\" + (i + 1), (screenWidth/2 - screenWidth/5 + i*(screenWidth/20)), (screenWidth/20));\n" +
			"\t\t\t//Image entImage = entImages.get(i);\n" +
			"\t\t\t//if(entImage != null){\n" +
			"\t\t\t//}\n" +
			"\t\t}\n" +
			"\t\t\n" +
			"\t\tif(player.getHealthPots() > 0){\n" +
			"\t\t\tImage hImage = entImages.get(-ENTITY_HEALTHPOT);\n" +
			"\t\t\tif(hImage != null){\n" +
			"\t\t\t\tg.drawImage(\n" +
			"\t\t\t\t\t\thImage,\t\n" +
			"\t\t\t\t\t\t(int)\t(screenWidth/2 - screenWidth/5), \n" +
			"\t\t\t\t\t\t0, \n" +
			"\t\t\t\t\t\t(int)\t(screenWidth/20), \n" +
			"\t\t\t\t\t\t(int)\t(screenWidth/20), \n" +
			"\t\t\t\t\t\tthis\n" +
			"\t\t\t\t);\n" +
			"\t\t\t\t\n" +
			"\t\t\t\tif(player.getHealthPots() > 1){\n" +
			"\t\t\t\t\tg.setColor(Color.WHITE);\n" +
			"\t\t\t\t\tg.setFont(defaultFont24);\n" +
			"\t\t\t\t\tg.drawString(\"\" + player.healthPots, (int)\t(screenWidth/2 - 3*screenWidth/20 - 24), 24);\n" +
			"\t\t\t\t}\n" +
			"\t\t\t}\n" +
			"\t\t\telse{\n" +
			"\t\t\t\tWRITE(\"The image was null\");\n" +
			"\t\t\t}\n" +
			"\t\t}\n" +
			"\t}\n" +
			"\t\n" +
			"\tprivate void drawLevel(Graphics2D g){\n" +
			"\t\t//background for information - must be behind level bar\n" +
			"\t\t//g.setColor(new Color(50, 50, 50));\n" +
			"\t\t//g.fillRect((int) (screenWidth/2 - 2*levelBarWidth/2), (int) (screenHeight - levelBarWidth), (int) (levelBarWidth * (5.0/2)), (int) (levelBarWidth * (4.0/5)));\n" +
			"\t\t\n" +
			"\t\t//background gray bar\n" +
			"\t\tg.setColor(new Color(50, 50, 50, 100));\n" +
			"\t\tg.fillRect(0, (int)(screenHeight - levelBarHeight), (int) screenWidth, levelBarHeight);\n" +
			"\t\t\n" +
			"\t\t//drawing the rects for bar separation\n" +
			"\t\tg.setColor(Color.BLACK);\n" +
			"\t\tfor(int i=0; i<10; i++){\n" +
			"\t\t\tg.drawRect(i * levelBarWidth, (int) (screenHeight - levelBarHeight), levelBarWidth, levelBarHeight);\n" +
			"\t\t}\n" +
			"\t\t\n" +
			"\t\t//drawing the status of next level in green\n" +
			"\t\tg.setColor(new Color(0, 150, 0, 100));\n" +
			"\t\tg.fillRect(0, (int)(screenHeight - levelBarHeight), (int) ((player.getLevelCompleted()) * screenWidth), levelBarHeight);\n" +
			"//\t\t\tg.setFont(new Font(\"Monospaced\", 0, (int) scale/2));\n" +
			"//\t\t\tg.drawString(\"r: \" + red, (int)(screenWidth/2), (int) (screenHeight - .5*levelBarWidth/5));\n" +
			"//\t\t\tg.drawString(\"g: \" + green, (int)(screenWidth/2), (int) (screenHeight - 1.5*levelBarWidth/5));\n" +
			"//\t\t\tg.drawString(\"b: \" + blue, (int)(screenWidth/2), (int) (screenHeight - 2.5*levelBarWidth/5));\n" +
			"\t\t\t\n" +
			"\t\t//draw stats information\n" +
			"\t}\n" +
			"\t\n" +
			"\tint statWindowHeight = 150;\n" +
			"\tint statWindowWidth = 300;\n" +
			"\tprivate void drawStats(Graphics2D g){\n" +
			"\t\t//stat background\n" +
			"\t\tg.setColor(semiTransparent);\n" +
			"\t\tg.fillRect(0, (int) (screenHeight - statWindowHeight - levelBarHeight), statWindowWidth, statWindowHeight);\n" +
			"\t\t\n" +
			"\t\t//level\n" +
			"\t\tg.setColor(Color.WHITE);\n" +
			"\t\tg.setFont(defaultFont24);//font size 24\n" +
			"\t\tString theLevelString = \"Level: \" + player.getLevel();\n" +
			"\t\tg.drawString(theLevelString, \n" +
			"\t\t\t\t(int)(statWindowWidth/2 - g.getFontMetrics(defaultFont24).stringWidth(theLevelString)/2), \n" +
			"\t\t\t\tscreenHeight - (int)(statWindowHeight*.8) - levelBarHeight);\n" +
			"\t\t\t\n" +
			"\t\t\n" +
			"\t\t//health bar\n" +
			"\t\tg.setColor(Color.RED);\n" +
			"\t\tg.drawRoundRect(\n" +
			"\t\t\t\t(int)(statWindowWidth*.05), \n" +
			"\t\t\t\t(int) screenHeight - (int)(statWindowHeight*.7) - levelBarHeight, \n" +
			"\t\t\t\t(int) (statWindowWidth * .9), \n" +
			"\t\t\t\t(int) (statWindowHeight * .2), \n" +
			"\t\t\t\t(int) (statWindowWidth/16), \n" +
			"\t\t\t\t(int) (statWindowHeight/16));\n" +
			"\t\tg.fillRoundRect(\n" +
			"\t\t\t\t(int)(statWindowWidth*.05), \n" +
			"\t\t\t\t(int) screenHeight - (int)(statWindowHeight*.7) - levelBarHeight, \n" +
			"\t\t\t\t(int) (statWindowWidth * .9 * (player.getHealth() / player.getMaxHealth())), \n" +
			"\t\t\t\t(int) (statWindowHeight * .2), \n" +
			"\t\t\t\t(int) (statWindowWidth/16), \n" +
			"\t\t\t\t(int) (statWindowHeight/16));\n" +
			"\t\n" +
			"\t\t//health information\n" +
			"\t\tg.setColor(Color.WHITE);\n" +
			"\t\tg.setFont(defaultFont24);//font size 24\n" +
			"\t\tString healthString = \"Health \" + (int)player.getHealth() + \"/\" + (int)player.getMaxHealth();\n" +
			"\t\tg.drawString(healthString, \n" +
			"\t\t\t\t(int)(statWindowWidth/2 - g.getFontMetrics(defaultFont24).stringWidth(healthString)/2), \n" +
			"\t\t\t\t(int) screenHeight - (int)(statWindowHeight*.55) - levelBarHeight);\n" +
			"\t\t\n" +
			"\t\t//for entities\n" +
			"\t\tg.drawImage(entImages.get(-ENTITY_WOOD),\n" +
			"\t\t\t\t(int)(statWindowWidth*.05),\n" +
			"\t\t\t\t(int)(screenHeight - levelBarHeight - (statWindowHeight * .25)),\n" +
			"\t\t\t\t(int)(statWindowWidth*.1),\n" +
			"\t\t\t\t(int)(statWindowWidth*.1),\n" +
			"\t\t\t\tnull);\n" +
			"\t\t\tg.drawString(\"\" + (int)player.getLumber(), \n" +
			"\t\t\t\t\t(int)(statWindowWidth*.15), \n" +
			"\t\t\t\t\t(int)(screenHeight - levelBarHeight - (statWindowHeight * .15)));\n" +
			"\t\tg.drawImage(entImages.get(-ENTITY_ROCK),\n" +
			"\t\t\t\t(int)(statWindowWidth*.35),\n" +
			"\t\t\t\t(int)(screenHeight - levelBarHeight - (statWindowHeight * .25)),\n" +
			"\t\t\t\t(int)(statWindowWidth*.1),\n" +
			"\t\t\t\t(int)(statWindowWidth*.1),\n" +
			"\t\t\t\tnull);\n" +
			"\t\t\tg.drawString(\"\" + (int)player.getRock(), \n" +
			"\t\t\t\t\t(int)(statWindowWidth*.45), \n" +
			"\t\t\t\t\t(int)(screenHeight - levelBarHeight - (statWindowHeight * .15)));\n" +
			"\t\tg.drawImage(entImages.get(-ENTITY_GOLD),\n" +
			"\t\t\t\t(int)(statWindowWidth*.65),\n" +
			"\t\t\t\t(int)(screenHeight - levelBarHeight - (statWindowHeight * .25)),\n" +
			"\t\t\t\t(int)(statWindowWidth*.1),\n" +
			"\t\t\t\t(int)(statWindowWidth*.1),\n" +
			"\t\t\t\tnull);\n" +
			"\t\t\tg.drawString(\"\" + (int)player.getGold(), \n" +
			"\t\t\t\t\t(int)(statWindowWidth*.75), \n" +
			"\t\t\t\t\t(int)(screenHeight - levelBarHeight - (statWindowHeight * .15)));\n" +
			"\t}\n" +
			"\t\n" +
			"\t//extra draw functions cast by paint method\n" +
			"\t//<editor-fold defaultstate=\"collapsed\">\n" +
			"\tprivate void drawStringBounce(Graphics2D g, String text, int x, int y){\n" +
			"\t\tint xOffset = g.getFontMetrics(defaultFont24).charWidth('H') * text.length()/2;\n" +
			"\t\tint yOffset = g.getFontMetrics(defaultFont24).getHeight()/2;\n" +
			"\tg.setFont(defaultFont24);\n" +
			"\t\tg.drawString(text, x - xOffset, \n" +
			"\t\t\t\t\t\t\t(int)(y - Math.abs((time%1000) - 500) / 50) - yOffset\n" +
			"\t\t);\n" +
			"\t}\n" +
			"\t\n" +
			"\tprivate double getTimeMilli(){\n" +
			"\t\treturn time%1000;\n" +
			"\t}\n" +
			"\t\n" +
			"\tprivate int getTimeSeconds(){\n" +
			"\t\treturn (int)((time / 1000) % 60);\n" +
			"\t}\n" +
			"\t\n" +
			"\tprivate int getTimeMinutes(){\n" +
			"\t\treturn (int) ((time / 1000) / 60);\n" +
			"\t}\n" +
			"\t\n" +
			"\tprivate void drawPlayer(Graphics2D g){\n" +
			"\t\tplayer.draw(characterMovement, g, screenWidth/2 - scale/2, screenHeight/2 - scale/2, scale, scale, scale, this, time, getMX(), getMY(), screenWidth, screenHeight);\n" +
			"\t\t//fix character 2.5d object translation\n" +
			"\t\tint drawingX = getDrawingOffsetX(scale) + (int) Math.round((player.getIntX() * scale));\n" +
			"\t\tint drawingY = getDrawingOffsetY(scale) + (int) Math.round((player.getIntY() * scale));\n" +
			"\t\t\n" +
			"\t\tfor(int j=1; j<=STANDARD_SCALE_DRAW_HEIGHT; j++){\n" +
			"\t\t\tfor(int i=-STANDARD_SCALE_DRAW_WIDTH; i<=STANDARD_SCALE_DRAW_WIDTH; i++){\n" +
			"\t\t\t\tdrawObject(g, drawingX, drawingY, i, j, (int)scale);\n" +
			"\t\t\t}\n" +
			"\t\t}\n" +
			"\t}\n" +
			"\t\n" +
			"\tprivate void drawMain(Graphics2D g){\n" +
			"\t\tg.setColor(Color.BLUE);\n" +
			"\t\tint drawingX = getDrawingOffsetX(scale) + (int) Math.round((player.getIntX() * scale));\n" +
			"\t\tint drawingY = getDrawingOffsetY(scale) + (int) Math.round((player.getIntY() * scale));\n" +
			"\t\tdrawChunk(g, drawingX, drawingY);\n" +
			"\t}\n" +
			"\t\n" +
			"\tprivate void drawObjects(Graphics2D g){\n" +
			"\t\tg.setColor(Color.BLUE);\n" +
			"\t\tint drawingX = getDrawingOffsetX(scale) + (int) Math.round((player.getIntX() * scale));\n" +
			"\t\tint drawingY = getDrawingOffsetY(scale) + (int) Math.round((player.getIntY() * scale));\n" +
			"\t\t//draw objects above player\n" +
			"\t\t\n" +
			"\t\tfor(int j=-STANDARD_SCALE_DRAW_HEIGHT; j<=0; j++){\n" +
			"\t\t\tfor(int i=-STANDARD_SCALE_DRAW_WIDTH; i<=STANDARD_SCALE_DRAW_WIDTH; i++){\n" +
			"\t\t\t\tdrawObject(g, drawingX, drawingY, i, j, (int)scale);\n" +
			"\t\t\t}\n" +
			"\t\t}\n" +
			"\t\t\n" +
			"\t}\n" +
			"\t\n" +
			"\tHashMap<Integer, Image> floorImages, decorImages, entImages;\n" +
			"\tColor blockHealthColor = new Color(0, 0, 255, 150);\n" +
			"\tprivate void drawChunk(Graphics2D g, int x, int y){\n" +
			"\t\t//draw floor tiles\n" +
			"\t\t\n" +
			"\t\tfor(int j=-STANDARD_SCALE_DRAW_HEIGHT; j<=STANDARD_SCALE_DRAW_HEIGHT; j++){\n" +
			"\t\t\tfor(int i=-STANDARD_SCALE_DRAW_WIDTH; i<=STANDARD_SCALE_DRAW_WIDTH; i++){\n" +
			"\t\t\t\tTile currentTile = map.getTile(player.getIntX() + i, player.getIntY() + j);\n" +
			"\t\t\t\t\n" +
			"\t\t\t\tif(currentTile != null){\n" +
			"\t\t\t\t\tImage floorImage = floorImages.get(currentTile.floor);\n" +
			"\t\t\t\t\tint theScale = (int)scale;\n" +
			"\n" +
			"\t\t\t\t\tif(floorImage != null){\n" +
			"\t\t\t\t\t\t//WRITE(\"a non-null image is available for drawing to floor\");\n" +
			"\t\t\t\t\t\tint ow = floorImage.getWidth(null);\n" +
			"\t\t\t\t\t\tint oh = floorImage.getHeight(null);\n" +
			"\t\t\t\t\t\t\n" +
			"\t\t\t\t\t\tint woffset = (int)Math.round(theScale/2 - ((ow/IMAGE_TILE_SIZE)*theScale)/2);\n" +
			"\t\t\t\t\t\tint hoffset = (int)Math.round(theScale/2 - ((oh/IMAGE_TILE_SIZE)*theScale)/2);\n" +
			"\t\t\t\t\t\tint width = (int)Math.round((ow/IMAGE_TILE_SIZE) * theScale);\n" +
			"\t\t\t\t\t\tint height = (int)Math.round((oh/IMAGE_TILE_SIZE) * theScale);\n" +
			"\t\t\t\t\t\t\n" +
			"\t\t\t\t\t\t//g.drawImage(floorImage, x + i * theScale, y + j * theScale, theScale, theScale, null);\n" +
			"\t\t\t\t\t\tg.drawImage(floorImage, x + i * theScale + woffset, y + j * theScale + hoffset, width, height, null);\n" +
			"\t\t\t\t\t}\n" +
			"\t\t\t\t}\n" +
			"\t\t\t}\n" +
			"\t\t}\n" +
			"\t\t\n" +
			"\t\t//fix for objects drawing after character is in the player drawing call\n" +
			"\t}\n" +
			"\t\n" +
			"\tprivate void drawObject(Graphics2D g, int x, int y, int i, int j, int theScale){\n" +
			"\t\tTile currentTile = map.getTile(player.getIntX() + i, player.getIntY() + j);\n" +
			"\t\t\t\t\n" +
			"\t\tif(currentTile != null){\n" +
			"\t\t\tImage objectImage = decorImages.get(currentTile.decor);\n" +
			"\t\t\t//int theScale = (int)scale;\n" +
			"\n" +
			"\t\t\tif(objectImage != null){\n" +
			"\t\t\t\tint ow = objectImage.getWidth(null);\n" +
			"\t\t\t\tint oh = objectImage.getHeight(null);\n" +
			"\t\t\t\tint woffset = (int)Math.round(theScale/2 - ((ow/IMAGE_TILE_SIZE)*theScale)/2);\n" +
			"\t\t\t\t//WRITE(\"image width is: \" + ow + \" and the offset is: \" + woffset);\n" +
			"\t\t\t\tint hoffset = (int)Math.round(theScale - ((oh/IMAGE_TILE_SIZE)*theScale));\n" +
			"\t\t\t\tint width = (int)Math.round((ow/IMAGE_TILE_SIZE) * theScale);\n" +
			"\t\t\t\tint height = (int)Math.round((oh/IMAGE_TILE_SIZE) * theScale);\n" +
			"\t\t\t\t\n" +
			"\t\t\t\tg.drawImage(objectImage, x + i * theScale + woffset, y + j * theScale + hoffset, width, height, null);\n" +
			"\t\t\t\t//g.fillRect(x + i*theScale , y + j*theScale, theScale, theScale);\n" +
			"\t\t\t\tif(!currentTile.maxHealth()){\n" +
			"\t\t\t\t\tg.setColor(blockHealthColor);\n" +
			"\t\t\t\t\tg.fillRect(x + i * theScale, y + j * theScale + hoffset, (int) (theScale * currentTile.percentHealth()), theScale/10);\n" +
			"\t\t\t\t}\n" +
			"\t\t\t\t\n" +
			"\t\t\t\tif(currentTile.decor == SPECIAL_SPAWN){\n" +
			"\t\t\t\t\tg.setColor(Color.YELLOW);\n" +
			"\t\t\t\t\tg.setFont(defaultFont24);\n" +
			"//\t\t\t\t\tg.drawString(\"Spawn\", x + i * theScale, \n" +
			"//\t\t\t\t\t\t\t(int)(y + j * theScale + hoffset - Math.abs((time%1000) - 500) / 50));\n" +
			"\t\t\t\t\t//drawStringBounce(g, \"Spawn\", x + i * theScale, y + j * theScale + hoffset);\n" +
			"\t\t\t\t}\n" +
			"\t\t\t\telse if(currentTile.decor == SPECIAL_SMITH){\n" +
			"\t\t\t\t\tg.setColor(Color.YELLOW);\n" +
			"\t\t\t\t\tg.setFont(defaultFont24);\n" +
			"//\t\t\t\t\tg.drawString(\"Blacksmith\", x + i * theScale - theScale/4, \n" +
			"//\t\t\t\t\t\t\t(int)(y + j * theScale + hoffset - Math.abs((time%1000) - 500) / 50));\n" +
			"\t\t\t\t\t//drawStringBounce(g, \"Blacksmith\", x + i * theScale, y + j * theScale + hoffset);\n" +
			"\t\t\t\t}\n" +
			"\t\t\t}\n" +
			"\t\t}\n" +
			"\t}\n" +
			"\t//</editor-fold>\n" +
			"\t\n" +
			"\t//</editor-fold>\n" +
			"\t\n" +
			"\t//methods for retrieving character location information\n" +
			"\t//<editor-fold defaultstate=\"collapsed\">\n" +
			"\tpublic int getMapChunkX(){\n" +
			"\t\tint mapX = (int) Math.floor((player.getX()) / (chunkSize));\n" +
			"\t\treturn mapX;\n" +
			"\t}\n" +
			"\t\n" +
			"\tpublic int getMapChunkY(){\n" +
			"\t\tint mapY = (int) Math.floor((player.getY()) / (chunkSize));\n" +
			"\t\treturn mapY;\n" +
			"\t}\n" +
			"\t\n" +
			"\t/*\n" +
			"\t\t\n" +
			"\t\tCan use getMapX(int) and getMapY(int) for collisions to test quickly if any if the four corners are contained by another square\n" +
			"\t\t\twill be more simple and faster than previous route which pretty much did the same thing but many more calls in order to get correct map x/y\n" +
			"\t\t\n" +
			"\t*/\n" +
			"\tpublic int getMapX(){\n" +
			"\t\t//return (int) Math.floor(player.getX());\n" +
			"\t\treturn getMapX((int) player.getX());\n" +
			"\t}\n" +
			"\t\n" +
			"\tpublic int getMapY(){\n" +
			"\t\t//return (int) Math.floor(player.getY());\n" +
			"\t\treturn getMapY((int) player.getY());\n" +
			"\t}\n" +
			"\t\n" +
			"\tpublic int getMapX(int x){\n" +
			"\t\treturn (int) Math.floor(x);\n" +
			"\t}\n" +
			"\t\n" +
			"\tpublic int getMapY(int y){\n" +
			"\t\treturn (int) Math.floor(y);\n" +
			"\t}\n" +
			"\t\n" +
			"\t//</editor-fold>\n" +
			"\t\n" +
			"\t//code for running the graphics engine thread\n" +
			"\t//<editor-fold defaultstate=\"collapsed\">\n" +
			"\tshort fps, ups, eps, cps;\n" +
			"\tshort repaints, updates, eupdates, cupdates;\n" +
			"\tlong\tlastFPSUpdate, currentGraphicsTime, \n" +
			"\t\t\tlastUPSUpdate, currentUpdateTime,\n" +
			"\t\t\tlastEPSUpdate, currentEUpdateTime,\n" +
			"\t\t\tlastCPSUpdate, currentCUpdateTime;\n" +
			"\tprivate class GraphicsEngine implements Runnable, ActionListener{\n" +
			"\t\tTimer time;\n" +
			"\t\t@Override\n" +
			"\t\tpublic void run() {\n" +
			"\t\t\tlastFPSUpdate = 0;\n" +
			"\t\t\tcurrentGraphicsTime = System.nanoTime();\n" +
			"\t\t\tfps = 0;\n" +
			"\t\t\ttime = new Timer(16, this);\n" +
			"\t\t\ttime.start();\n" +
			"\t\t\tgraphicsUpdate();\n" +
			"\t\t}\n" +
			"\t\t\n" +
			"\t\tprivate void graphicsUpdate(){\n" +
			"//\t\t\twhile(!closeDown){\n" +
			"\t\t\t\t//sleeps for 8 milli seconds to create cap - caps cpu usage\n" +
			"//\t\t\t\ttry {\n" +
			"//\t\t\t\t\tsleep(8);\n" +
			"//\t\t\t\t} catch (InterruptedException ex) {\n" +
			"//\t\t\t\t\tWRITE(\"ERROR - on trying to sleep in graphics thread\");\n" +
			"//\t\t\t\t}\n" +
			"\t\t\t\trepaint();\n" +
			"\t\t\t\tif(closeDown){\n" +
			"\t\t\t\t\ttime.stop();\n" +
			"\t\t\t\t}\n" +
			"//\t\t\t}\n" +
			"\t\t}\n" +
			"\n" +
			"\t\t@Override\n" +
			"\t\tpublic void actionPerformed(ActionEvent e) {\n" +
			"\t\t\tgraphicsUpdate();\n" +
			"\t\t}\n" +
			"\t}\n" +
			"\t//</editor-fold>\n" +
			"\t\n" +
			"\t//Working with projectiles\n" +
			"\t//<editor-fold defaultstate=\"collapsed\">\n" +
			"\tArrayList<Projectile> playerProjectiles;\n" +
			"\tImage[] buckshotImages, dashImages, arrowImages, knifeImages;\n" +
			"\tImage buckIcon, dashIcon, arrowIcon;\n" +
			"\tvoid setupProjectileImages(){\n" +
			"\t\tbuckshotImages = new Image[] {\t(new ImageIcon(\"images/character/weapon/buckshot1.png\")).getImage(),\n" +
			"\t\t\t\t\t\t\t\t\t\t(new ImageIcon(\"images/character/weapon/buckshot2.png\")).getImage(),\n" +
			"\t\t\t\t\t\t\t\t\t\t(new ImageIcon(\"images/character/weapon/buckshot3.png\")).getImage(),\n" +
			"\t\t\t\t\t\t\t\t\t\t(new ImageIcon(\"images/character/weapon/buckshot4.png\")).getImage(),\n" +
			"\t\t\t\t\t\t\t\t\t\t(new ImageIcon(\"images/character/weapon/buckshot5.png\")).getImage(),\n" +
			"\t\t\t\t\t\t\t\t\t\t(new ImageIcon(\"images/character/weapon/buckshot6.png\")).getImage(),\n" +
			"\t\t\t\t\t\t\t\t\t\t(new ImageIcon(\"images/character/weapon/buckshot7.png\")).getImage(),\n" +
			"\t\t\t\t\t\t\t\t\t\t(new ImageIcon(\"images/character/weapon/buckshot8.png\")).getImage(),\n" +
			"\t\t\t\t\t\t\t\t\t\t(new ImageIcon(\"images/character/weapon/buckshot9.png\")).getImage()\n" +
			"\t\t\t\t\t\t\t\t\t\t};\n" +
			"\t\tbuckIcon = (new ImageIcon(\"images/character/weapon/icon.png\")).getImage();\n" +
			"\t\tdashImages = new Image[] {\t(new ImageIcon(\"images/character/ability/dash1.png\")).getImage(),\n" +
			"\t\t\t\t\t\t\t\t\t(new ImageIcon(\"images/character/ability/dash2.png\")).getImage(),\n" +
			"\t\t\t\t\t\t\t\t\t(new ImageIcon(\"images/character/ability/dash3.png\")).getImage(),\n" +
			"\t\t\t\t\t\t\t\t\t(new ImageIcon(\"images/character/ability/dash4.png\")).getImage(),\n" +
			"\t\t\t\t\t\t\t\t\t(new ImageIcon(\"images/character/ability/dash5.png\")).getImage(),\n" +
			"\t\t\t\t\t\t\t\t\t(new ImageIcon(\"images/character/ability/dash6.png\")).getImage()\n" +
			"\t\t\t\t\t\t\t\t\t};\n" +
			"\t\tdashIcon = (new ImageIcon(\"images/character/ability/icon.png\")).getImage();\n" +
			"\t\t\n" +
			"\t\tarrowImages = new Image[] {\t(new ImageIcon(\"images/character/weapon/ammo1.png\")).getImage(),\n" +
			"\t\t\t\t\t\t\t\t\t(new ImageIcon(\"images/character/weapon/ammo2.png\")).getImage(),\n" +
			"\t\t\t\t\t\t\t\t\t(new ImageIcon(\"images/character/weapon/ammo3.png\")).getImage(),\n" +
			"\t\t\t\t\t\t\t\t\t(new ImageIcon(\"images/character/weapon/ammo4.png\")).getImage()};\n" +
			"\t\t\n" +
			"\t\tarrowIcon = (new ImageIcon(\"images/character/weapon/ammoIcon.png\")).getImage();\n" +
			"\t}\n" +
			"\t\n" +
			"\tvoid moveProjectiles(){\n" +
			"\t\tif(playerProjectiles != null){\n" +
			"\t\t\tfor(int i=0; i<playerProjectiles.size(); i++){\n" +
			"\t\t\t\tProjectile current = playerProjectiles.get(i);\n" +
			"\t\t\t\tcurrent.move();\n" +
			"\t\t\t}\n" +
			"\t\t}\n" +
			"\t}\n" +
			"\t\n" +
			"\tvoid checkRemoveProjectiles(){\n" +
			"\t\tfor(int i=0; i<playerProjectiles.size(); i++){\n" +
			"\t\t\t//WRITE(\"the spawn timer difference is: \" + (time - playerProjectiles.get(i).getSpawnTime()));\n" +
			"\t\t\tProjectile bullet = playerProjectiles.get(i);\n" +
			"\t\t\tif(bullet.intersects(map, entities, eid, time)){\n" +
			"\t\t\t\tplayerProjectiles.remove(i);\n" +
			"\t\t\t}\n" +
			"\t\t\telse if(time >= bullet.getSpawnTime() + bullet.getDelay()){\n" +
			"\t\t\t\tplayerProjectiles.remove(i);\n" +
			"\t\t\t}\n" +
			"\t\t\telse if(bullet.getTeam() == TEAM_PLAYER){\n" +
			"\t\t\t\tfor(int m = -2; m <= 2; m++){\n" +
			"\t\t\t\t\tfor(int k = -3; k <= 3; k++){\n" +
			"\t\t\t\t\t\tChunk current = map.getFromCoord(getMapChunkX() + k, getMapChunkY() + m);\n" +
			"\t\t\t\t\t\tif(current != null){\n" +
			"\t\t\t\t\t\t\tcheckPlayerProjectileEnemyDamage(current.getEnemyList(), bullet);\n" +
			"\t\t\t\t\t\t}\n" +
			"\t\t\t\t\t}\n" +
			"\t\t\t\t}\n" +
			"\t\t\t}\n" +
			"\t\t\t/*\n" +
			"\t\t\t\tcheck if the current square of the projectile is a solid tile - check with the middle of the square\n" +
			"\t\t\t*/\n" +
			"\t\t}\n" +
			"\t}\n" +
			"\t\n" +
			"\tvoid checkPlayerProjectileEnemyDamage(ArrayList<Unit> enemies, Projectile bullet){\n" +
			"\t\tUnit enemyHit = null;\n" +
			"\t\t\n" +
			"\t\tfor(int j=0; j<enemies.size(); j++){\n" +
			"\t\t\tUnit current = enemies.get(j);\n" +
			"\t\t\tif(fullIntersects(bullet.getX(), bullet.getY(), bullet.getWidth(), bullet.getHeight(), current.getX(), current.getY(), current.getWidth(), current.getHeight())){\n" +
			"\t\t\t\tcurrent.persue = true;\n" +
			"\t\t\t\tint dead = current.damage(bullet.getAttackBonus());\n" +
			"\t\t\t\tif(dead == 1){\n" +
			"\t\t\t\t\taddEnemyLoot(enemies.get(j));\n" +
			"\t\t\t\t\tquests.checkKillingQuests(player, current);\n" +
			"\t\t\t\t\tenemies.remove(j);\n" +
			"\t\t\t\t\tplayer.addExperience(GType.scaledExperienceFromLevel(current.getLevel()));\n" +
			"\t\t\t\t}\n" +
			"\t\t\t\tenemyHit = current;\n" +
			"\t\t\t}\n" +
			"\t\t}\n" +
			"\t\t\n" +
			"\t\tif(enemyHit != null){\n" +
			"\t\t\tplayerProjectiles.remove(bullet);\n" +
			"\t\t\teffects.add(new Effect(enemyHit.getX() + .5, enemyHit.getY() + .5, .75, .75, GType.EFFECT_BLOOD, time));\n" +
			"\t\t}\n" +
			"\t}\n" +
			"\t//</editor-fold>\n" +
			"\t\n" +
			"\t//working with entities\n" +
			"\tArrayList<Entity> entities;\n" +
			"\tHashMap<Integer, Integer> eid;\n" +
			"\tprivate void setupEntities(){\n" +
			"\t\tentities = new ArrayList<>();\n" +
			"\t}\n" +
			"\t\n" +
			"\tprivate void checkEntityDespawn(){\n" +
			"\t\tfor(int i=0; i<entities.size(); i++){\n" +
			"\t\t\tEntity current = entities.get(i);\n" +
			"\t\t\tif(current.shouldDespawn(time)){\n" +
			"\t\t\t\tentities.remove(i);\n" +
			"\t\t\t}\n" +
			"\t\t}\n" +
			"\t}\n" +
			"\t\n" +
			"\tprivate void checkEntityPickup(){\n" +
			"\t\t/*\n" +
			"\t\t\tif the entity collides with player... pickup... if entity is close enough\n" +
			"\t\t*/\n" +
			"\t\tfor(int i=0; i<entities.size(); i++){\n" +
			"\t\t\tEntity current = entities.get(i);\n" +
			"\t\t\tif(current.intersects(player)){\n" +
			"\t\t\t\tplayer.addEntity(current);\n" +
			"\t\t\t\tquests.checkCollectionQuests(player);\n" +
			"\t\t\t\tentities.remove(i);\n" +
			"\t\t\t}\n" +
			"\t\t}\n" +
			"\t}\n" +
			"\t\n" +
			"\tprivate void drawEntities(Graphics2D g){\n" +
			"\t\tfor(int i=0; i<entities.size(); i++){\n" +
			"\t\t\tentities.get(i).draw(g, entImages, time, getDrawingOffsetX(scale), getDrawingOffsetY(scale), scale, this);\n" +
			"\t\t}\n" +
			"\t}\n" +
			"\t\n" +
			"\t/*\n" +
			"\t\n" +
			"\t\tCode for enemy loot will be here, basically segregated to this method with the killed enemy parameter incase\n" +
			"\t\t\tlevel is needed to determine loot\n" +
			"\t\tCan add potions like other entities...\n" +
			"\t\n" +
			"\t*/\n" +
			"\tprivate void addEnemyLoot(Unit killedEnemy){\n" +
			"\t\tif(entities != null){\n" +
			"\t\t\tdouble chance = Math.random();\n" +
			"\t\t\tif(chance < .5){\n" +
			"\t\t\t\tentities.add(new Entity(killedEnemy.getX() + .5, killedEnemy.getY() + .5, -ENTITY_GOLD, PP_NONE, ENTITY_GOLD, time));\n" +
			"\t\t\t}\n" +
			"\t\t\t\n" +
			"\t\t\tchance = Math.random();\n" +
			"\t\t\tif(chance < .05){\n" +
			"\t\t\t\tentities.add(new Entity(killedEnemy.getX(), killedEnemy.getY(), -ENTITY_HEALTHPOT, PP_NONE, ENTITY_HEALTHPOT, time));\n" +
			"\t\t\t}\n" +
			"\t\t\t\n" +
			"\t\t\tchance = Math.random();\n" +
			"\t\t\tif(chance < .1){\n" +
			"\t\t\t\tentities.add(new Entity(killedEnemy.getX() + .5, killedEnemy.getY(), -ENTITY_HEALTH, PP_NONE, ENTITY_HEALTH, time));\n" +
			"\t\t\t}\n" +
			"\t\t}\n" +
			"\t}\n" +
			"\t//code for key press action events\n" +
			"\t//<editor-fold defaultstate=\"collapsed\">\n" +
			"\tboolean left, right, up, down;\n" +
			"\tclass KE implements KeyListener {\n" +
			"\n" +
			"\t\t@Override\n" +
			"\t\tpublic void keyTyped(KeyEvent e) {\n" +
			"\t\t\t\n" +
			"\t\t}\n" +
			"\n" +
			"\t\t@Override\n" +
			"\t\tpublic void keyPressed(KeyEvent e) {\n" +
			"\t\t\t//WRITE(\"a key was pressed\");\n" +
			"\t\t\tint key = e.getKeyCode();\n" +
			"\t\t\tif(key == KeyEvent.VK_W){\n" +
			"\t\t\t\tup = true;\n" +
			"\t\t\t\tplayer.setDY(-1);\n" +
			"\t\t\t}\n" +
			"\t\t\tif(key == KeyEvent.VK_A){\n" +
			"\t\t\t\tleft = true;\n" +
			"\t\t\t\tplayer.setDX(-1);\n" +
			"\t\t\t}\n" +
			"\t\t\tif(key == KeyEvent.VK_S){\n" +
			"\t\t\t\tdown = true;\n" +
			"\t\t\t\tplayer.setDY(1);\n" +
			"\t\t\t}\n" +
			"\t\t\tif(key == KeyEvent.VK_D){\n" +
			"\t\t\t\tright = true;\n" +
			"\t\t\t\tplayer.setDX(1);\n" +
			"\t\t\t}\n" +
			"\t\t\tif(key == KeyEvent.VK_SPACE){\n" +
			"\t\t\t\tif(gameOver){\n" +
			"\t\t\t\t\trespawnPlayer();\n" +
			"\t\t\t\t}\n" +
			"\t\t\t}\n" +
			"\t\t\tif(key == KeyEvent.VK_M){\n" +
			"\t\t\t\t//if(!paused || displayingMap){\n" +
			"\t\t\t\tdisplayingMap = !displayingMap;\n" +
			"//\t\t\t\t\tif(displayingMap){\n" +
			"//\t\t\t\t\t\tpaused = true;\n" +
			"//\t\t\t\t\t}\n" +
			"//\t\t\t\t\telse{\n" +
			"//\t\t\t\t\t\tpaused = false;\n" +
			"//\t\t\t\t\t}\n" +
			"\t\t\t\t//}\n" +
			"\t\t\t}\n" +
			"\t\t\tif(key == KeyEvent.VK_F3){\n" +
			"\t\t\t\tshowDeveloperStats = !showDeveloperStats;\n" +
			"\t\t\t}\n" +
			"\t\t\t\n" +
			"\t\t\t//for inventory\n" +
			"\t\t\tif(key == KeyEvent.VK_1){\n" +
			"\t\t\t\tplayer.useHealthPot();\n" +
			"\t\t\t}\n" +
			"\t\t\tplayer.setAngle();\n" +
			"\t\t\t\n" +
			"\t\t\tquests.checkKeyActionQuests(player, key, true);\n" +
			"\t\t}\n" +
			"\n" +
			"\t\t@Override\n" +
			"\t\tpublic void keyReleased(KeyEvent e) {\n" +
			"\t\t\tint key = e.getKeyCode();\n" +
			"\t\t\tif(key == KeyEvent.VK_W){\n" +
			"\t\t\t\tif(down){\n" +
			"\t\t\t\t\tplayer.setDY(1);\n" +
			"\t\t\t\t}\n" +
			"\t\t\t\telse{\n" +
			"\t\t\t\t\tplayer.setDY(0);\n" +
			"\t\t\t\t}\n" +
			"\t\t\t\tup = false;\n" +
			"\t\t\t}\n" +
			"\t\t\tif(key == KeyEvent.VK_A){\n" +
			"\t\t\t\tif(right){\n" +
			"\t\t\t\t\tplayer.setDX(1);\n" +
			"\t\t\t\t}\n" +
			"\t\t\t\telse{\n" +
			"\t\t\t\t\tplayer.setDX(0);\n" +
			"\t\t\t\t}\n" +
			"\t\t\t\tleft = false;\n" +
			"\t\t\t}\n" +
			"\t\t\tif(key == KeyEvent.VK_S){\n" +
			"\t\t\t\tif(up){\n" +
			"\t\t\t\t\tplayer.setDY(-1);\n" +
			"\t\t\t\t}\n" +
			"\t\t\t\telse{\n" +
			"\t\t\t\t\tplayer.setDY(0);\n" +
			"\t\t\t\t}\n" +
			"\t\t\t\tdown = false;\n" +
			"\t\t\t}\n" +
			"\t\t\tif(key == KeyEvent.VK_D){\n" +
			"\t\t\t\tif(left){\n" +
			"\t\t\t\t\tplayer.setDX(-1);\n" +
			"\t\t\t\t}\n" +
			"\t\t\t\telse{\n" +
			"\t\t\t\t\tplayer.setDX(0);\n" +
			"\t\t\t\t}\n" +
			"\t\t\t\tright = false;\n" +
			"\t\t\t}\n" +
			"\t\t\tplayer.setAngle();\n" +
			"\t\t\t\n" +
			"\t\t\tquests.checkKeyActionQuests(player, key, false);\n" +
			"\t\t}\n" +
			"\t\t\n" +
			"\t}\n" +
			"\t\n" +
			"\tboolean m1Held, m2Held;\n" +
			"\tint m1x, m2x;\n" +
			"\tclass ME implements MouseListener {\n" +
			"\t\t@Override\n" +
			"\t\tpublic void mouseClicked(MouseEvent e) {\n" +
			"\t\t}\n" +
			"\n" +
			"\t\t@Override\n" +
			"\t\tpublic void mousePressed(MouseEvent e) {\n" +
			"\t\t\tif(!gameOver){\n" +
			"\t\t\t\tint mouseButton = e.getButton();\n" +
			"\t\t\t\tif(mouseButton == MouseEvent.BUTTON1){\n" +
			"\t\t\t\t\tplayer.startAttack(time);\n" +
			"\t\t\t\t\t//m1Held = true;\n" +
			"\t\t\t\t\t//tryPlayerAttack(getMX(), getMY(), 1);\n" +
			"\t\t\t\t}\n" +
			"\t\t\t\tif(mouseButton == MouseEvent.BUTTON3){\n" +
			"\t\t\t\t\tplayer.cancelAttack(time);\n" +
			"\t\t\t\t\t//m2Held = true;\n" +
			"\t//\t\t\t\ttryPlayerAttack(getMX(), getMY(), 2);\n" +
			"\t\t\t\t/*\n" +
			"\t\t\t\t\tinstead change button two to adding a block??\n" +
			"\t\t\t\t*/\n" +
			"\t\t\t\t}\n" +
			"\t\t\t\tquests.checkMouseActionQuests(player, mouseButton, true);\n" +
			"\t\t\t}\n" +
			"\t\t}\n" +
			"\n" +
			"\t\t@Override\n" +
			"\t\tpublic void mouseReleased(MouseEvent e) {\n" +
			"\t\t\tif(!gameOver){\n" +
			"\t\t\t\tint mouseButton = e.getButton();\n" +
			"\t\t\t\t//int x = e.getX();\n" +
			"\t\t\t\t//int y = e.getY();\n" +
			"\t\t\t\t//WRITE(\"The location is: \" + locationX + \" | \" + locationY);\n" +
			"\t\t\t\tif(mouseButton == MouseEvent.BUTTON1){\n" +
			"\t\t\t\t\tplayer.releaseAttack(playerProjectiles, screenWidth, screenHeight, getMX(), getMY(), time);\n" +
			"\t\t\t\t\t//m1Held = false;\n" +
			"\t\t\t\t}\n" +
			"\t\t\t\tif(mouseButton == MouseEvent.BUTTON3){\n" +
			"\t\t\t\t\t\n" +
			"\t\t\t\t}\n" +
			"\t\t\t\tquests.checkMouseActionQuests(player, mouseButton, false);\n" +
			"\t\t\t}\n" +
			"\t\t}\n" +
			"\n" +
			"\t\t@Override\n" +
			"\t\tpublic void mouseEntered(MouseEvent e) {\n" +
			"\t\t}\n" +
			"\n" +
			"\t\t@Override\n" +
			"\t\tpublic void mouseExited(MouseEvent e) {\n" +
			"\t\t}\n" +
			"\t}\n" +
			"\t\n" +
			"//\tvoid tryRepeatingMouse(){\n" +
			"//\t\tif(m1Held){\n" +
			"//\t\t\t//tryPlayerAttack(getMX(), getMY(), 1);\n" +
			"//\t\t}\n" +
			"//\t\tif(m2Held){\n" +
			"////\t\t\ttryPlayerAttack(getMX(), getMY(), 2);\n" +
			"//\t\t\t/*\n" +
			"//\t\t\t\tsame as above, change to adding block or something\n" +
			"//\t\t\t*/\n" +
			"//\t\t}\n" +
			"//\t}\n" +
			"\t\n" +
			"//\tvoid tryPlayerAttack(int mx, int my, int move){\n" +
			"//\t\tif(player.canAttack(time, move)){\n" +
			"////\t\t\tdouble angle = Math.toDegrees(Tools.EMath.getAngleRad((int) (screenWidth/2), (int) (screenHeight/2), mx, my));\n" +
			"////\t\t\t//WRITE(\"\" + angle);\n" +
			"////\t\t\tif(angle < 0){\n" +
			"////\t\t\t\tangle += 360;\n" +
			"////\t\t\t}\n" +
			"////\t\t\t//WRITE(\"The angle is: \" + angle);\n" +
			"////\t\t\tdouble xExtra = 0, yExtra = 0;\n" +
			"////\t\t\t\n" +
			"////\t\t\t\n" +
			"////\t\t\t/*\n" +
			"////\t\t\t\tThe x and y offset needs to be adjusted here hardcoded depending on how big the projectile is\n" +
			"////\t\t\t\t(to center projectile around character)\n" +
			"////\t\t\t\n" +
			"////\t\t\t\tthe xextra and yextra is how far the projectile should be from the center\n" +
			"////\t\t\t*/\n" +
			"////\t\t\txExtra = 0 * Math.cos(angle);\n" +
			"////\t\t\tyExtra = 0 * Math.sin(angle);\n" +
			"////\t\t\t\n" +
			"////\t\t\tif(move == 1){\n" +
			"////\t\t\t\t//for the primary attack\n" +
			"////\t\t\t\tdouble dx = 2 * Math.cos(Math.toRadians(angle)) / 8;\n" +
			"////\t\t\t\tdouble dy = -2 * Math.sin(Math.toRadians(angle)) / 8;\n" +
			"////\t\t\t\tProjectile newProjectile = new Projectile(player.getX() + xExtra + .5, player.getY() + yExtra + .5, \n" +
			"////\t\t\t\t\t\t\t\t\t\t\t\t\t\t\t.5, .5, angle, \n" +
			"////\t\t\t\t\t\t\t\t\t\t\t\t\t\t\ttime, dx, dy, \n" +
			"////\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tTEAM_PLAYER, player.getAttackBonus(), \n" +
			"////\t\t\t\t\t\t\t\t\t\t\t\t\t\t\tGType.STANDARD_PROJECTILE_DESPAWN_TIME, 300);\n" +
			"////\t\t\t\t//newProjectile.setBufferedImage(arrowImages, this, scale);\n" +
			"////\t\t\t\tplayerProjectiles.add(newProjectile);\n" +
			"////\t\t\t}\n" +
			"//\t\t}\n" +
			"//\t}\n" +
			"\t\n" +
			"\tdouble getDirection(int m1x, int m1y, int m2x, int m2y){\n" +
			"\t\tdouble degrees =  Math.toDegrees(Math.atan2(m1y - m2y, m2x - m1x ));\n" +
			"\t\treturn degrees;\n" +
			"\t}\n" +
			"\t\n" +
			"\tint getMX(){\n" +
			"\t\ttry{\n" +
			"\t\t\treturn (int) (MouseInfo.getPointerInfo().getLocation().getX() - this.getLocationOnScreen().getX());\n" +
			"\t\t}catch(IllegalComponentStateException ex){\n" +
			"\t\t\treturn 0;\n" +
			"\t\t}\n" +
			"\t}\n" +
			"\t\n" +
			"\tint getMY(){\n" +
			"\t\ttry{\n" +
			"\t\t\treturn (int) (MouseInfo.getPointerInfo().getLocation().getY() - this.getLocationOnScreen().getY());\n" +
			"\t\t}catch(IllegalComponentStateException ex){\n" +
			"\t\t\treturn 0;\n" +
			"\t\t}\n" +
			"\t}\n" +
			"\t//</editor-fold>\n" +
			"\t\n" +
			"\t/*\n" +
			"\tFor crafting\n" +
			"\t\tcode for repair/upgrade/placing blocks\n" +
			"\t\t\t-1 for repair/upgrade tool\n" +
			"\t\t\t0-n for placing blocks\n" +
			"\t*/\n" +
			"\t//<editor-fold defaultstate=\"collapsed\">\n" +
			"//\tint tool = -1;\n" +
			"//\tBuiltBlock[] blocks;\n" +
			"//\tint inventorySize = 20;\n" +
			"//\tprivate void setupCrafting(){\n" +
			"//\t\tblocks = new BuiltBlock[inventorySize];\n" +
			"//\t}\n" +
			"\t//</editor-fold>\n" +
			"}\n";

}
