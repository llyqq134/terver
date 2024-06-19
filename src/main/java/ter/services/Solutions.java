package ter.services;

import org.apache.commons.math3.distribution.NormalDistribution;

import java.io.IOException;
import java.util.*;
import java.util.List;

public class Solutions {
	static Random r = new Random();

	public static void mainFunc(List<Integer> chosenTasks, List<List<Double>> params, int option, int numPages)
			throws IOException {
		String path = System.getProperty("user.home") + "\\Downloads\\";
		String fileName = "Вариант " + option + ".docx";
		FileManager taskFile = new FileManager(path, fileName);
		fileName = "Ответы " + option + ".docx";
		FileManager answerFile = new FileManager(path, fileName);
		for (int i = 0; i < numPages; ++i) {
			taskFile.WriteRegularText("вариант " + option + "." + (i + 1));
			answerFile.WriteRegularText("Ответы на вариант " + option + "." + (i + 1));

			for (var taskId : chosenTasks) {
				switch (taskId) {
				case (1): {
					taskFile.WriteRegularText("Задача 1");
					answerFile.WriteRegularText("Задача 1");
					task1(params.get(0), option, answerFile, taskFile);
					break;
				}
				case (2): {
					taskFile.WriteRegularText("Задача 2");
					answerFile.WriteRegularText("Задача 2");
					task2(params.get(1), option, answerFile, taskFile);
					break;
				}
				case (3): {
					taskFile.WriteRegularText("Задача 3");
					answerFile.WriteRegularText("Задача 3");
					task3(params.get(2), option, answerFile, taskFile);
					break;
				}
				case (4): {
					taskFile.WriteRegularText("Задача 4");
					answerFile.WriteRegularText("Задача 4");
					task4(params.get(3), option, answerFile, taskFile);
					break;
				}
				case (5): {
					taskFile.WriteRegularText("Задача 5");
					answerFile.WriteRegularText("Задача 5");
					task5(params.get(4), option, answerFile, taskFile);
					break;
				}
				case (6): {
					taskFile.WriteRegularText("Задача 6");
					answerFile.WriteRegularText("Задача 6");
					task6(params.get(5), option, answerFile, taskFile);
					break;
				}
				case (7): {
					taskFile.WriteRegularText("Задача 7");
					answerFile.WriteRegularText("Задача 7");
					task7(params.get(6), option, answerFile, taskFile);
					break;
				}
				case (8): {
					taskFile.WriteRegularText("Задача 8");
					answerFile.WriteRegularText("Задача 8");
					task8(params.get(7), option, answerFile, taskFile);
					break;
				}
				case (9): {
					taskFile.WriteRegularText("Задача 9");
					answerFile.WriteRegularText("Задача 9");
					task9(params.get(8), option, answerFile, taskFile);
					break;
				}
				case (10): {
					taskFile.WriteRegularText("Задача 10");
					answerFile.WriteRegularText("Задача 10");
					task10(params.get(9), option, answerFile, taskFile);
					break;
				}
				case (11): {
					taskFile.WriteRegularText("Задача 11");
					answerFile.WriteRegularText("Задача 11");
					task11(option, answerFile, taskFile);
					break;
				}
				case (12): {
					taskFile.WriteRegularText("Задача 12");
					answerFile.WriteRegularText("Задача 12");
					task12(params.get(11), option, answerFile, taskFile);
					break;
				}
				case (13): {
					taskFile.WriteRegularText("Задача 13");
					answerFile.WriteRegularText("Задача 13");
					task13(params.get(12), option, answerFile, taskFile);
					break;
				}
				case (14): {
					taskFile.WriteRegularText("Задача 14");
					answerFile.WriteRegularText("Задача 14");
					task14(option, answerFile, taskFile);
					break;
				}
				case (15): {
					taskFile.WriteRegularText("Задача 15");
					answerFile.WriteRegularText("Задача 15");
					task15(params.get(14), option, answerFile, taskFile);
					break;
				}
				case (16): {
					taskFile.WriteRegularText("Задача 16");
					answerFile.WriteRegularText("Задача 16");
					task16(params.get(15), option, answerFile, taskFile);
					break;
				}
				default: {
					taskFile.WriteRegularText("Задача 17");
					answerFile.WriteRegularText("Задача 17");
					task17(option, answerFile, taskFile);
					break;
				}
				}
			}
			if (i != numPages - 1) {
				taskFile.addBreakPage();
				answerFile.addBreakPage();
			}
		}
	}

	public static double mathAwait(int[] val, double[] chances) {
		double sum = 0;
		for (int i = 0; i < val.length; ++i) {
			sum += val[i] * chances[i];
		}
		return sum;
	}

	public static double dispersion(int[] val, double[] chances) {
		double sum = 0;
		var ma = mathAwait(val, chances);
		for (int i = 0; i < val.length; ++i) {
			sum += Math.pow(val[i] - ma, 2) * chances[i];
		}
		return sum;
	}

	public static double sqrDeviation(double dispers) {
		return Math.sqrt(dispers);
	}

	public static long Factorial(int n) {
		long result = 1;
		for (int i = 2; i <= n; i++) {
			result *= i;
		}
		return result;
	}

	public static double Combinations(int n, int k) {
		return Factorial(n) / (Factorial(k) * Factorial(n - k));
	}

	public static double Bernulli(int n, int k, double p) {
		return Combinations(n, k) * Math.pow(p, k) * Math.pow(1 - p, n - k);
	}

	public static String stringBernulli(int n, int k, double p) {
		return "C(" + n + ',' + k + ") * " + p + "^" + k + " * " + (p - 1) + "^" + (n - k);
	}

	public static double localLaplas(int n, int k, double p) {
		return 1 / Math.sqrt(n * p * (1 - p)) * 1 / Math.sqrt(Math.PI * 2)
				* Math.exp(-1 / 2 * Math.pow((k - n * p) / Math.sqrt(n * p * (1 - p)), 2));
	}

	public static double Puasson(int n, int k, double p) {
		return Math.pow(n * p, k) / Factorial(k) * Math.exp(-n * p);
	}

	public static String[] arrToStr(double[] arr) {
		String[] newArr = new String[arr.length];
		for (int i = 0; i < arr.length; ++i) {
			newArr[i] = arr[i] + "";
		}
		return newArr;
	}

	public static double calculateSigma(double mean, double lower, double upper, double probability) {
		NormalDistribution standardNormal = new NormalDistribution();
		double zLower = standardNormal.inverseCumulativeProbability((1 - probability) / 2);
		double zUpper = standardNormal.inverseCumulativeProbability((1 + probability) / 2);

		double sigma = (upper - lower) / (zUpper - zLower);
		return sigma;
	}

	public static double calculateProbability(double mean, double sigma, double lower, double upper) {
		NormalDistribution normalDistribution = new NormalDistribution(mean, sigma);
		double probability = normalDistribution.cumulativeProbability(upper)
				- normalDistribution.cumulativeProbability(lower);
		return probability;
	}

	public static void task1(List<Double> params, int opt, FileManager ansFile, FileManager tskFile) {
		if (opt == 1) {
			int womenAmt = params.get(r.nextInt(params.size())).intValue();
			float possibilities = Factorial(womenAmt);

			tskFile.WriteRegularText(String.format("У ювелира имеется %d различных драгоценных "
					+ "камней, и каждый из них по гороскопу народов Барбадоса "
					+ "соответствует одному из знаков зодиака. %d дам, ро"
					+ "дившихся под разными знаками зодиака и не знакомых с "
					+ "культурой Барбадоса, купили у ювелира по одному драго"
					+ "ценному камню. Какова вероятность того, что: "
					+ "а) каждой даме достался камень, соответствующий ее " + "знаку;"
					+ " б) самой юной даме достался «ее» камень?", womenAmt, womenAmt));
			ansFile.WriteRegularText("answer for a) " + String.format("%.6f", 1.0 / possibilities));
			ansFile.WriteRegularText("answer for b) " + String.format("%.4f", Factorial(womenAmt - 1) / possibilities));
		} else {

			int TEF = params.get(r.nextInt(params.size())).intValue();
			int EMF = params.get(r.nextInt(params.size())).intValue();
			int IMEC = params.get(r.nextInt(params.size())).intValue();
			int MF = params.get(r.nextInt(params.size())).intValue();

			long possibilities = Factorial(TEF + EMF + IMEC + MF);

			tskFile.WriteRegularText(String.format("На олимпиаде по математике в десятку сильнейших попали %d команды"
					+ " теплоэнергетического факультета (ТЭФ), %d команды электромеханического факультета (ЭМФ), %d "
					+ "команды — института менеджмента и экономики (ИМЭК) и %d команда — механического факультета (МФ). "
					+ "Какова вероятность того, что:" + " а) все призовые места будут заняты командами ТЭФа;"
					+ " б) на первом, втором и третьем месте окажутся команды ЭМФа, МФа " + "и ИМЭКа соответственно?",
					TEF, EMF, IMEC, MF));
			ansFile.WriteRegularText("answer for a) " + String.format("%.6f", Combinations(TEF, 3)
					* (double) Factorial(3) * (double) Factorial(EMF + IMEC + MF) / (double) possibilities));
			ansFile.WriteRegularText(
					"answer for b) " + String.format("%.4f", Combinations(EMF, 1) * Combinations(IMEC, 2)
							* Combinations(MF, 1) * (double) Factorial(EMF + TEF + IMEC + MF - 3) / possibilities));
		}
	}

	public static void task2(List<Double> params, int opt, FileManager ansFile, FileManager tskFile) {
		if (opt == 1) {
			int bel = params.get(r.nextInt(params.size())).intValue();
			int pros = params.get(r.nextInt(params.size())).intValue();
			int sel = params.get(r.nextInt(params.size())).intValue();

			long possibilities = (long) Combinations((bel + pros + sel), 3);

			double possibilityA = Combinations(pros, 2) / possibilities;
			double possibilityB = Combinations(bel, 2) * Combinations(sel, 1) / possibilities;

			tskFile.WriteRegularText(String.format("В ресторане на острове Занзибар в аквариуме ждут "
					+ "своей участи рыбы: %d бельдюги, %d простипомы и %d сельди. "
					+ "Официант сачком наугад вылавливает 3 рыбы. Какова ве" + "роятность того, что он поймал: "
					+ "а) две простипомы; " + "б) две бельдюги и сельдь?", bel, pros, sel));
			ansFile.WriteRegularText("answer for a) " + String.format("%.4f", possibilityA));
			ansFile.WriteRegularText("answer for b) " + String.format("%.4f", possibilityB));
		} else {
			int app = params.get(r.nextInt(params.size())).intValue();
			int pea = params.get(r.nextInt(params.size())).intValue();
			int ora = params.get(r.nextInt(params.size())).intValue();

			long possibilities = (long) Combinations(app + pea + ora, 4);
			double possibilityA = Combinations(app, 2) * Combinations(pea, 2) / possibilities;
			double possibilityB = Combinations(app, 1) * Combinations(pea, 2) * Combinations(ora, 1) / possibilities;

			tskFile.WriteRegularText(String.format("В корзине лежат %d яблок, %d груш и %d апельсина. На"
					+ "таша наугад вынимает четыре фрукта. Какова вероят" + "ность, что среди них: "
					+ "а) два яблока и две груши; " + "б) яблоко, апельсин и две груши?", app, pea, ora));
			ansFile.WriteRegularText("answer for a) " + String.format("%.4f", possibilityA));
			ansFile.WriteRegularText("answer for b) " + String.format("%.4f", possibilityB));
		}
	}

	public static void task3(List<Double> params, int opt, FileManager ansFile, FileManager tskFile) {// вторая глава
																										// начинается со
																										// второго
																										// задания
		if (opt == 1) {
			double probEng = params.get(r.nextInt(params.size()));
			double probEco = params.get(r.nextInt(params.size()));

			double possibilityA = probEng * probEco;
			double possibilityB = probEng * (1 - probEco);
			double possibilityC = 1 - ((1 - probEng) * (1 - probEco));

			tskFile.WriteRegularText(String.format(
					"Двое безработных — экономист и инженер-меха"
							+ "ник — пытаются найти работу через бюро по трудоустрой"
							+ "ству. Вероятность того, что работу получит экономист, "
							+ "равна %f, для инженера такая вероятность равна %f. Ка"
							+ "кова вероятность того, что сегодня: " + "а) оба безработных получат работу; "
							+ "б) работу получит только инженер; " + "в) кто нибудь из них получит работу?",
					probEco, probEng));
			ansFile.WriteRegularText("answer for a) " + String.format("%.4f", possibilityA));
			ansFile.WriteRegularText("answer for b) " + String.format("%.4f", possibilityB));
			ansFile.WriteRegularText("answer for c) " + String.format("%.4f", possibilityC));
		} else {
			double probKarp = params.get(r.nextInt(params.size()));
			double probKaras = params.get(r.nextInt(params.size()));

			double possibilityA = probKarp * (1 - probKaras) + probKaras * (1 - probKarp);
			double possibilityB = probKaras * (1 - probKarp);
			double possibilityC = (1 - probKarp) * (1 - probKaras);

			tskFile.WriteRegularText(String.format("Николай Петрович любит рыбачить на озере. Веро"
					+ "ятность выловить в начале рыбалки карпа равна %f, а ка"
					+ "рася — %f. Какова вероятность, что в начале рыбалки: "
					+ "а) Николай Петрович поймал только одну из этих рыб; " + "б) поймал только карася; "
					+ "в) ничего не поймал.", probKarp, probKaras));
			ansFile.WriteRegularText("answer for a) " + String.format("%.4f", possibilityA));
			ansFile.WriteRegularText("answer for b) " + String.format("%.4f", possibilityB));
			ansFile.WriteRegularText("answer for c) " + String.format("%.4f", possibilityC));
		}
	}

	public static void task4(List<Double> params, int opt, FileManager ansFile, FileManager tskFile) {

		double probSasha = params.get(r.nextInt(params.size()));
		double probMisha = params.get(r.nextInt(params.size()));

		double possibilityA = Math.pow(probMisha, 3) * probSasha * 4 + Math.pow(probMisha, 4);

		tskFile.WriteRegularText(String.format(
				"Саша и Миша собирают вкладыши от жевательной " + "резинки. Мама Саши покупает ему нужную жевательную "
						+ "резинку с вероятностью %f , а мама Миши — с вероятно"
						+ "стью %f . Через некоторое время оказалось, что у ребят "
						+ "4 новых вкладыша. Какова вероятность, что большинст" + "во из них Мишины?",
				probSasha, probMisha));
		ansFile.WriteRegularText("answer for a) " + String.format("%.4f", possibilityA));
	}

	public static void task5(List<Double> params, int opt, FileManager ansFile, FileManager tskFile) {
		if (opt == 1) {
			double possibilityA = 12 / Combinations(10, 4);

			tskFile.WriteRegularText(String.format("Из букв разрезной азбуки составлено слово «МАТЕ"
					+ "МАТИКА». Буквы перемешивают. Какова вероятность "
					+ "того, что, выкладывая в ряд взятые наугад 4 буквы, полу" + "чим слово «ТЕМА»"));
			ansFile.WriteRegularText("answer for a) " + String.format("%.4f", possibilityA));
		} else {
			int nodeAmt = params.get(r.nextInt(params.size())).intValue();
			double offChance = 1 / (double) nodeAmt;

			double possibilityA = Math.pow(offChance, 2) * Combinations(nodeAmt, 2)
					* Math.pow(1 - offChance, nodeAmt - 2);

			tskFile.WriteRegularText(String.format("Прибор состоит из %d узлов, и если один из них "
					+ "выходит из строя, прибор прекращает работу. Последова"
					+ "тельная замена каждого узла новым производится до тех "
					+ "пор, пока прибор не начнет работать. Какова вероятность "
					+ "того, что придется заменить 2 узла?", nodeAmt));
			ansFile.WriteRegularText("answer for a) " + String.format("%.4f", possibilityA));
		}
	}

	public static void task6(List<Double> params, int opt, FileManager ansFile, FileManager tskFile) {
		if (opt == 1) {
			int penAmt = params.get(r.nextInt(params.size())).intValue();

			double possibilityA = 0.5 + 0.5 / (double) penAmt;

			tskFile.WriteRegularText(String.format("В коробку, содержащую %d одинаковые ручки, поло"
					+ "жили еще одну — с красным стержнем. Затем наугад вы"
					+ "нули одну ручку. Найти вероятность того, что извлекли "
					+ "ручку с красным стержнем, если равновероятны все воз"
					+ "можные предположения о числе ручек с красным стерж"
					+ "нем, первоначально находящихся в коробке.", penAmt));
			ansFile.WriteRegularText("answer for a) " + String.format("%.4f", possibilityA));
		} else {
			int box1_grey = params.get(r.nextInt(params.size())).intValue();
			int box1_white = params.get(r.nextInt(params.size())).intValue();
			int box2_grey = params.get(r.nextInt(params.size())).intValue();
			int box2_white = params.get(r.nextInt(params.size())).intValue();
			int box3_grey = params.get(r.nextInt(params.size())).intValue();
			int box3_white = params.get(r.nextInt(params.size())).intValue();

			double possibilityA = 0.33 * ((double) box1_white / (box1_white + box1_grey)
					+ (double) box2_white / (box2_white + box2_grey) + (double) box3_white / (box3_white + box3_grey));

			tskFile.WriteRegularText(String.format(
					"В трех одинаковых по виду ящиках сидят мыши. " + "В первом — %d белые и %d серая, во втором — %d "
							+ "белые и %d серые, в третьем — %d белые и %d серые. Ка"
							+ "кова вероятность того, что из наугад выбранного ящика " + "будет извлечена белая мышь?",
					box1_white, box1_grey, box2_white, box2_grey, box3_white, box3_grey));
			ansFile.WriteRegularText("answer for a) " + String.format("%.6f", possibilityA));
		}
	}

	public static void task7(List<Double> params, int opt, FileManager ansFile, FileManager tskFile) {
		if (opt == 1) {
			int d16 = params.get(r.nextInt(params.size())).intValue();
			int zh16 = params.get(r.nextInt(params.size())).intValue();
			int z16 = params.get(r.nextInt(params.size())).intValue();

			double studentAccepted = 0.9 * d16 + 0.8 * zh16 + 0.5 * z16;
			double d16ch = 0.9 * d16 / studentAccepted;
			double zh16ch = 0.8 * zh16 / studentAccepted;
			double z16ch = 0.9 * z16 / studentAccepted;
			double highest = Math.max(d16ch, Math.max(zh16ch, z16ch));

			tskFile.WriteRegularText(String.format("Для участия в математической олимпиаде среди сту"
					+ "дентов ОмГУПСа из группы 16д выбрано %d человека, из "
					+ "группы 16ж — %d и из группы 16з — %d. Вероятность того, "
					+ "что студент попадет в команду механического факульте"
					+ "та, для этих групп равна 0,9, 0,8 и 0,5 соответственно. "
					+ "Наугад выбранный студент в итоге попал в команду. В ка"
					+ "кой из групп вероятнее всего он учился?", d16, zh16, z16));
			if (highest == d16ch) {
				ansFile.WriteRegularText("в команде 16д");
			} else if (highest == zh16ch) {
				ansFile.WriteRegularText("в команде 16ж");
			} else {
				ansFile.WriteRegularText("в команде 16з");
			}
		} else {
			int goodTickets = params.get(r.nextInt(params.size() - 3)).intValue();
			int badTickets = params.get(r.nextInt(params.size() - 3) + 3).intValue();
			int allTickets = goodTickets + badTickets;

			double possibility = 1.0 * (goodTickets - 1) / (allTickets - 1) * goodTickets / allTickets
					+ goodTickets / (allTickets - 1) * badTickets / allTickets;

			tskFile.WriteRegularText(String.format("На экзамене студентам предлагается %d билетов, %d "
					+ "из которых легкие, а %d — трудные. Два студента по оче"
					+ "реди тянут билеты — сначала первый, затем второй. Вто"
					+ "рому повезло — достался легкий билет. Какова вероят"
					+ "ность того, что и первый вытянул легкий билет?", allTickets, goodTickets, badTickets));
			ansFile.WriteRegularText("answer for a) " + String.format("%.4f", possibility));
		}
	}

	public static void task8(List<Double> params, int opt, FileManager ansFile, FileManager tskFile) {
		if (opt == 1) {
			double chance = params.get(r.nextInt(params.size()));
			double possibilityA = 0;
			for (int i = 5; i <= 8; ++i) {
				possibilityA += Bernulli(8, i, chance);
			}

			tskFile.WriteRegularText(String.format("Вероятность успешно выполнить штрафной бросок "
					+ "мячом по корзине для спортсмена равна %f . Найти веро"
					+ "ятность того, что во время игры из восьми выполненных "
					+ "им штрафных бросков больше половины окажутся успеш" + "ными", chance));
			ansFile.WriteRegularText("answer for a) " + String.format("%.4f", possibilityA));
		} else {
			double chance = params.get(r.nextInt(params.size()));
			double possibilityA = 0;
			for (int i = 2; i <= 4; ++i) {
				possibilityA += Bernulli(8, i, chance / 10);
			}

			tskFile.WriteRegularText(String.format("Каждое %d из 10 поворотное реле для автомобиля при "
					+ "покупке его в автомагазине оказывается дефектным. Ка"
					+ "кова вероятность того, что при покупке 4 реле не менее " + "половины из них качественные?",
					(int) chance));
			ansFile.WriteRegularText("answer for a) " + String.format("%.4f", possibilityA));
		}
	}

	public static void task9(List<Double> params, int opt, FileManager ansFile, FileManager tskFile) {
		if (opt == 1) {
			int chosenAmt = params.get(r.nextInt(params.size())).intValue();

			double possibilityA = localLaplas(chosenAmt, 40, 0.8);

			tskFile.WriteRegularText(String.format("В урне 80 белых и 20 черных шаров. Какова вероят"
					+ "ность того, что при %d независимых выборах шара (с воз" + "вращением) будет вынуто: "
					+ "а) половина шаров белого цвета; ", chosenAmt));
			ansFile.WriteRegularText("answer for a) " + String.format("%.4f", possibilityA));
		} else {
			int chosenAmt = params.get(r.nextInt(params.size())).intValue();

			double possibilityA = localLaplas(chosenAmt, 35, 0.6);

			tskFile.WriteRegularText(String.format("Известно, что только 6 из 10 младенцев вскармли"
					+ "ваются грудным молоком. На участке педиатра Ивановой "
					+ "наблюдается %d детей в возрасте до одного года. Какова "
					+ "вероятность того, что на ее участке: " + "а) 35 детей находятся на грудном вскармливании;",
					chosenAmt));
			ansFile.WriteRegularText("answer for a) " + String.format("%.4f", possibilityA));
		}
	}

	public static void task10(List<Double> params, int opt, FileManager ansFile, FileManager tskFile) {
		if (opt == 1) {
			int denied = params.get(r.nextInt(params.size())).intValue();

			double possibilityA = Puasson(100, denied, 0.008);

			tskFile.WriteRegularText(String.format("Вероятность возникновения опасной для прибора "
					+ "перегрузки в каждом опыте равна 0,04. Во время пере"
					+ "грузки прибор отказывает с вероятностью 0,2. Найти ве"
					+ "роятность отказа %d приборов в серии из 100 опытов", denied));
			ansFile.WriteRegularText("answer for a) " + String.format("%.4f", possibilityA));
		} else {
			int denied = params.get(r.nextInt(params.size())).intValue();

			double possibilityA = Puasson(10000, denied, 0.0001);

			tskFile.WriteRegularText(String.format("Учебник издан тиражом 10 000 экземпляров. Веро"
					+ "ятность того, что экземпляр учебника неправильно сбро"
					+ "шюрован, равна 0,0001. Найдите вероятность того, что "
					+ "тираж содержит ровно %d бракованных книг.", denied));
			ansFile.WriteRegularText("answer for a) " + String.format("%.4f", possibilityA));
		}
	}

	public static void task11(int opt, FileManager ansFile, FileManager tskFile) {
		if (opt == 1) {
			int[] val = { 0, 200, 600, 800, 1500, 1700, 2100 };
			double[] chances = { (double) 96 * 97 / 9900, (double) 2 * 97 / 9900, (double) 2 * 97 / 9900,
					(double) 2 / 9900, (double) 2 * 97 / 9900, (double) 2 / 9900, (double) 2 / 9900 };

			double mathAwt = mathAwait(val, chances);
			double dispers = dispersion(val, chances);
			double sqDev = sqrDeviation(dispers);

			String[] strChances = arrToStr(chances);

			tskFile.WriteRegularText(String.format("В лотерее на 100 билетов разыгрываются три вещи, "
					+ "стоимость которых 1500, 200 и 600 руб. Составить ряд рас"
					+ "пределения суммы выигрыша для лица, имеющего два "
					+ "билета. Найти М(Х), D(X), √D(X), F(X) этой случайной ве" + "личины. "));
			ansFile.WriteTable(val, strChances, "x");
			ansFile.WriteRegularText("мат. ожидание " + String.format("%.4f", mathAwt));
			ansFile.WriteRegularText("дисперсия " + String.format("%.4f", dispers));
			ansFile.WriteRegularText("среднее квадратическое отклонение " + String.format("%.4f", sqDev));
		} else {
			int[] val = { 0, 1, 2, 3, 4, 5, 6, 7, 8 };
			double[] chances = { 0.043, 0.173, 0.258, 0.218, 0.137, 0.055, 0.008, 0.0008, 0.0001 };
			double mathAwt = mathAwait(val, chances);
			double dispers = dispersion(val, chances);
			double sqDev = sqrDeviation(dispers);

			String[] strChances = arrToStr(chances);

			tskFile.WriteRegularText(String.format("Подсчитано, что треть женщин, посещающих про"
					+ "довольственный магазин, покупает обезжиренный йогурт. "
					+ "Составить ряд распределения числа женщин, купивших "
					+ "обезжиренный йогурт, если магазин посетили 8 женщин. "
					+ "Найти М(Х), D(X), √D(X), F(X) этой случайной величины."));
			ansFile.WriteTable(val, strChances, "x");
			ansFile.WriteRegularText("мат. ожидание " + String.format("%.6f", mathAwt));
			ansFile.WriteRegularText("дисперсия " + String.format("%.6f", dispers));
			ansFile.WriteRegularText("среднее квадратическое отклонение " + String.format("%.6f", sqDev));

		}
	}

	public static void task12(List<Double> params, int opt, FileManager ansFile, FileManager tskFile) {
		if (opt == 1) {
			double chance = params.get(r.nextInt(params.size()));

			int[] var = new int[4];
			double[] chances = new double[4];
			for (int i = 0; i < 4; ++i) {
				var[i] = i;
				chances[i] = Bernulli(3, i, chance);
			}

			double mathAwt = 3 * chance;
			double dispers = mathAwt * (1 - chance);

			String[] strChances = arrToStr(chances);

			tskFile.WriteRegularText(String.format("На ремонте в депо стоят три вагона. Вероятность "
					+ "того, что они будут отремонтированы своевременно, рав"
					+ "на для каждого %f. Составить ряд распределения числа "
					+ "вагонов, которые будут отремонтированы своевременно. "
					+ "Найти M(X) и D(X) этой случайной величины.", chance));
			ansFile.WriteTable(var, strChances, "x");
			ansFile.WriteRegularText("мат. ожидание " + String.format("%.4f", mathAwt));
			ansFile.WriteRegularText("дисперсия " + String.format("%.4f", dispers));
		} else {
			double chance = params.get(r.nextInt(params.size()));

			int[] var = new int[5];
			double[] chances = new double[5];
			for (int i = 0; i < 5; ++i) {
				var[i] = i;
				chances[i] = Bernulli(4, i, chance);
			}

			double mathAwt = 4 * chance;
			double dispers = mathAwt * (1 - chance);

			String[] strChances = arrToStr(chances);

			tskFile.WriteRegularText(String.format("Школьник решает 4 примера по математике. Ве"
					+ "роятность сделать ошибку в вычислениях одного примера "
					+ "равна %f . Составить ряд распределения числа правильно "
					+ "решенных примеров. Найти M(X) и D(X) этой случайной " + "величины.", chance));
			ansFile.WriteTable(var, strChances, "x");
			ansFile.WriteRegularText("мат. ожидание " + String.format("%.4f", mathAwt));
			ansFile.WriteRegularText("дисперсия " + String.format("%.4f", dispers));
		}
	}

	public static void task13(List<Double> params, int opt, FileManager ansFile, FileManager tskFile) {
		if (opt == 1) {
			double pA = params.get(r.nextInt(params.size()));
			int tries = 80;
			double mathAwt = tries * pA;

			String[] ans = new String[4];
			int[] x = { 0, 1, 79, 80 };
			for (int i = 0; i < 4; ++i) {
				ans[i] = stringBernulli(80, x[i], pA);
				System.out.println(ans[i]);
			}

			tskFile.WriteRegularText(String.format("Вероятность появления события А в одном испы"
					+ "тании равна %f. Составить ряд распределения числа по"
					+ "явлений события А в 80 испытаниях. Найти M(X) этой " + "случайной величины.", pA));
			ansFile.WriteTable(x, ans, "x");
			ansFile.WriteRegularText("мат. ожидание " + String.format("%.4f", mathAwt));
		} else {
			double pA = params.get(r.nextInt(params.size()));
			int tries = 150;
			double mathAwt = tries * pA;

			String[] ans = new String[4];
			int[] x = { 0, 1, 149, 150 };
			for (int i = 0; i < 4; ++i) {
				ans[i] = stringBernulli(150, x[i], pA);
			}

			tskFile.WriteRegularText(String.format(
					"Вероятность того, что покупателю потребуется обувь "
							+ "38-го размера, равна %f. Составить ряд распределения "
							+ "числа покупателей, которые потребуют обувь 38-го раз"
							+ "мера, среди 150 посетителей обувного магазина. Найти " + "M(X) этой случайной величины.",
					pA));
			ansFile.WriteTable(x, ans, "x");
			ansFile.WriteRegularText("мат. ожидание = " + String.format("%.4f", mathAwt));
		}

	}

	public static void task14(int opt, FileManager ansFile, FileManager tskFile) {
		if (opt == 1) {
			int[] x = { -4, -1, 2 };
			double[] chancesX = { 0.4, 0.2, 0.4 };

			int[] y = { 1, 3 };
			double[] chancesY = { 0.3, 0.7 };

			double mathAwtX = mathAwait(x, chancesX);
			double mathAwtY = mathAwait(y, chancesY);

			double disperX = dispersion(x, chancesX);
			double disperY = dispersion(y, chancesY);

			int[] sumTb = new int[6];
			int[] prodTb = new int[6];
			double[] newChances = new double[6];
			int tempInd = 0;

			for (int i = 0; i < x.length; ++i) {
				for (int j = 0; j < y.length; ++j) {
					sumTb[tempInd] = 2 * x[i] + y[j];
					newChances[tempInd] = chancesX[i] * chancesY[j];
					tempInd++;
				}
			}

			tempInd = 0;
			for (int i = 0; i < x.length; ++i) {
				for (int j = 0; j < y.length; ++j) {
					prodTb[tempInd] = x[i] * y[j];
					tempInd++;
				}
			}

			double mathAwtZ1 = mathAwait(sumTb, newChances);
			double mathAwtZ2 = mathAwait(prodTb, newChances);
			double disperZ1 = dispersion(sumTb, newChances);
			double disperZ2 = dispersion(prodTb, newChances);

			String[] chanc = arrToStr(newChances);

			tskFile.WriteRegularText(String
					.format("Независимые случайные величины X и Y заданы таб" + "лицами распределений. " + "Найти: "
							+ "1) M(X), M(Y), D(X), D(Y); " + "2) таблицы распределения случайных величин Z1 "
							+ "= 2X + Y, Z2 = X * Y; " + "3) M(Z1), M(Z2), D(Z1), D(Z2) непосредственно по таб"
							+ "лицам распределений и на основании свойств математи" + "ческого ожидания и дисперсии."));
			tskFile.WriteRegularText("таблица X \n");
			tskFile.WriteTable(x, arrToStr(chancesX), "X");
			tskFile.WriteRegularText("таблица Y \n");
			tskFile.WriteTable(y, arrToStr(chancesY), "Y");
			ansFile.WriteRegularText("мат. ожидание X = " + String.format("%.4f", mathAwtX));
			ansFile.WriteRegularText("мат. ожидание Y = " + String.format("%.4f", mathAwtY));
			ansFile.WriteRegularText("дисперсия X = " + String.format("%.4f", disperX));
			ansFile.WriteRegularText("дисперсия Y = " + String.format("%.4f", disperY));
			ansFile.WriteRegularText("таблица Z1 \n");
			ansFile.WriteTable(sumTb, chanc, "Z1");
			ansFile.WriteRegularText("таблица Z2 \n");
			ansFile.WriteTable(prodTb, chanc, "Z2");
			ansFile.WriteRegularText("мат. ожидание Z1 = " + String.format("%.4f", mathAwtZ1));
			ansFile.WriteRegularText("мат. ожидание Z2 = " + String.format("%.4f", mathAwtZ2));
			ansFile.WriteRegularText("дисперсия Z1 = " + String.format("%.4f", disperZ1));
			ansFile.WriteRegularText("дисперсия Z2 = " + String.format("%.4f", disperZ2));

		} else {
			int[] x = { 1, 4, 6 };
			double[] chancesX = { 0.1, 0.3, 0.6 };

			int[] y = { 1, 4 };
			double[] chancesY = { 0.5, 0.5 };

			double mathAwtX = mathAwait(x, chancesX);
			double mathAwtY = mathAwait(y, chancesY);

			double disperX = dispersion(x, chancesX);
			double disperY = dispersion(y, chancesY);

			int[] sumTb = new int[6];
			int[] prodTb = new int[6];
			double[] newChances = new double[6];
			int tempInd = 0;

			for (int i = 0; i < x.length; ++i) {
				for (int j = 0; j < y.length; ++j) {
					sumTb[tempInd] = 2 * x[i] + y[j];
					newChances[tempInd] = chancesX[i] * chancesY[j];
					tempInd++;
				}
			}

			tempInd = 0;
			for (int i = 0; i < x.length; ++i) {
				for (int j = 0; j < y.length; ++j) {
					prodTb[tempInd] = x[i] * y[j];
					tempInd++;
				}
			}

			double mathAwtZ1 = mathAwait(sumTb, newChances);
			double mathAwtZ2 = mathAwait(prodTb, newChances);
			double disperZ1 = dispersion(sumTb, newChances);
			double disperZ2 = dispersion(prodTb, newChances);

			String[] chanc = arrToStr(newChances);

			tskFile.WriteRegularText(String
					.format("Независимые случайные величины X и Y заданы таб" + "лицами распределений. " + "Найти: "
							+ "1) M(X), M(Y), D(X), D(Y); " + "2) таблицы распределения случайных величин Z1 "
							+ "= 2X+Y, Z2 = X * Y; " + "3) M(Z1), M(Z2), D(Z1), D(Z2) непосредственно по таб"
							+ "лицам распределений и на основании свойств математи" + "ческого ожидания и дисперсии."));
			tskFile.WriteRegularText("таблица X \n");
			tskFile.WriteTable(x, arrToStr(chancesX), "X");
			tskFile.WriteRegularText("таблица Y \n");
			tskFile.WriteTable(y, arrToStr(chancesY), "Y");
			ansFile.WriteRegularText("мат. ожидание X = " + String.format("%.4f", mathAwtX));
			ansFile.WriteRegularText("мат. ожидание Y = " + String.format("%.4f", mathAwtY));
			ansFile.WriteRegularText("дисперсия X = " + String.format("%.4f", disperX));
			ansFile.WriteRegularText("дисперсия Y = " + String.format("%.4f", disperY));
			ansFile.WriteRegularText("таблица Z1 \n");
			ansFile.WriteTable(sumTb, chanc, "Z1");
			ansFile.WriteRegularText("таблица Z2 \n");
			ansFile.WriteTable(prodTb, chanc, "Z2");
			ansFile.WriteRegularText("мат. ожидание Z1 = " + String.format("%.4f", mathAwtZ1));
			ansFile.WriteRegularText("мат. ожидание Z2 = " + String.format("%.4f", mathAwtZ2));
			ansFile.WriteRegularText("дисперсия Z1 = " + String.format("%.4f", disperZ1));
			ansFile.WriteRegularText("дисперсия Z2 = " + String.format("%.4f", disperZ2));
		}
	}

	public static void task15(List<Double> params, int opt, FileManager ansFile, FileManager tskFile) {
		if (opt == 1) {
			double divVal = params.get(r.nextInt(params.size()));
			double eps = 0.01;
			double a = -0.5 * divVal;
			double b = 0.5 * divVal;
			double delta = b - a;

			double distrLaw = ((eps - a) / delta - (-eps - a) / delta);

			tskFile.WriteRegularText(String.format(
					"Цена деления шкалы измерительного прибора рав"
							+ "на %f . Показания округляются до ближайшего деления. "
							+ "Найти вероятность того, что при отсчете будет сделана " + "ошибка, меньшая 0,01.",
					divVal));
			ansFile.WriteRegularText("вероятность равна" + String.format("%.4f", distrLaw));
		} else {
			int mistake = params.get(r.nextInt(params.size())).intValue();
			double stdDeviation = 15;

			NormalDistribution normalDistribution = new NormalDistribution(0, stdDeviation);

			double probability = normalDistribution.cumulativeProbability(mistake)
					- normalDistribution.cumulativeProbability(-mistake);

			tskFile.WriteRegularText(String.format("Происходит взвешивание некоторого вещества. "
					+ "Систематические ошибки взвешивания отсутствуют, а "
					+ "случайные подчинены нормальному закону с  = 15 г. С ка"
					+ "кой вероятностью ошибка очередного взвешивания не пре" + "взойдет по абсолютной величине %d г?",
					mistake));
			ansFile.WriteRegularText("вероятность равна  " + String.format("%.4f", probability));
		}
	}

	public static void task16(List<Double> params, int opt, FileManager ansFile, FileManager tskFile) {
		if (opt == 1) {
			int mean = (int) (params.get(r.nextInt(params.size())) * 100);
			double lambda = 1.0 / mean;

			double t1 = 240;
			double t2 = 480;
			double t3 = 1000;

			double possibilityA = 1 - Math.exp(-lambda * t1);
			double possibilityB = Math.exp(-lambda * t1) - Math.exp(-lambda * t2);
			double possibilityC = Math.exp(-lambda * t3);

			tskFile.WriteRegularText(String.format("Время T безотказной работы установки рентген"
					+ "контроля аккумуляторных батарей имеет показательное "
					+ "распределение с математическим ожиданием %d ч. Ка"
					+ "кова вероятность того, что данная установка проработает " + "до выхода из строя: "
					+ "а) менее 240 ч; " + "б) от 240 до 480 ч; " + "в) более 1000 ч?", mean));
			ansFile.WriteRegularText("вероятность A) " + String.format("%.4f", possibilityA));
			ansFile.WriteRegularText("вероятность B) " + String.format("%.4f", possibilityB));
			ansFile.WriteRegularText("вероятность C) " + String.format("%.4f", possibilityC));
		} else {
			double lambda = params.get(r.nextInt(params.size())) / (100);

			double mathAwt = 1 / lambda;
			double disper = 1 / Math.pow(lambda, 2);
			double sqDev = Math.sqrt(disper);

			tskFile.WriteRegularText(String.format("Вероятность выхода из строя опорного подшип"
					+ "ника за время эксплуатации t задается формулой: Р(t) = " + "1 – e^(-%f t) "
					+ ". Случайная величина Т — время безотказной "
					+ "эксплуатации подшипника (в часах). Найти числовые ха"
					+ "рактеристики случайной величины: M(T), D(T), √D(T)", lambda));
			ansFile.WriteRegularText("мат. ожидание " + String.format("%.4f", mathAwt));
			ansFile.WriteRegularText("дисперсия " + String.format("%.4f", disper));
			ansFile.WriteRegularText("среднее квадратическое отклонение " + String.format("%.4f", sqDev));
		}
	}

	public static void task17(int opt, FileManager ansFile, FileManager tskFile) {
		if (opt == 1) {
			double mean = 25;
			double lowerBound1 = 10;
			double upperBound1 = 15;
			double probability1 = 0.1;

			double sigma = calculateSigma(mean, lowerBound1, upperBound1, probability1);

			double lowerBound2 = 35;
			double upperBound2 = 40;

			double probability2 = calculateProbability(mean, sigma, lowerBound2, upperBound2);

			tskFile.WriteRegularText(String.format("Случайная величина Х распределена нормально с "
					+ "математическим ожиданием m = 25. Вероятность попада"
					+ "ния Х в интервал (10; 15) равна 0,1. Чему равна вероят"
					+ "ность попадания Х в интервал (35; 40)?"));
			ansFile.WriteRegularText("вероятность " + String.format("%.4f", probability2));
		} else {
			double mathAwt = 25;
			double standardDeviation = 6;

			NormalDistribution distribution = new NormalDistribution(mathAwt, standardDeviation);

			double lowerBound = 20;
			double upperBound = 30;

			double probability = distribution.probability(lowerBound, upperBound);

			tskFile.WriteRegularText(String.format("Случайная величина Х имеет распределение Гаус"
					+ "са с плотностью вероятности: " + "f(x) = 1 / 6 √П * e ^ -1/36 * (x-25)^2. "
					+ "Определить вероятность попадания значения Х в интер"
					+ "вал (20; 30)"));
			ansFile.WriteRegularText("вероятность " + String.format("%f", probability));
		}
	}
}